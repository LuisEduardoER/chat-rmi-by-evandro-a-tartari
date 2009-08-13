package forms;

import gerenteDeTelas.Gerente;
import interfaces.IMensageiroCliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import util.Criptografia;
import util.Util;
import util.render.ComboCellRender;
import acao.FormListFriendsListener;
import acao.ToasterListener;
import br.com.chat.poptorradeira.Toaster;
import cliente.Mensagem;
import cliente.SysTrayClient.TrayManagerFormListFriend;
import cliente.ThreadsCliente.ThreadCarregaContatos;
import contatos.Contatos;
import contatos.ContatosComparator;
import contatos.render.ContatosRender;

/**
 * 
 * @author evandro.tartari
 * 
 */
public class FormListFriends extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -4098352435404830798L;
    private JScrollPane scrollContatos;
    private JToolBar painelBotoes;
    private JPanel painelUsuario;
    private JPanel painelStatus;
    private DefaultListModel modelContatos;
    private JList listaContatos;
    private IMensageiroCliente cliente;
    private Gerente gerente;
    private JButton btnOpenDownloads;
    private JButton btnChat;
    private JButton btnAdicionaContato;
    private JButton btnRemoveContato;
    private JComboBox status;
    private TrayManagerFormListFriend trayManager;
    private FormListFriendsListener listener;
    private List<Contatos> modelAux;
    private List<Contatos> listaApresentacao;
    private Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
    private Boolean isListaAberta = true;
    private JLabel lblUsuario;
    private Toaster toaster;
    private ToasterListener toasterListener;

    public FormListFriends(Gerente gerente) {
        this.gerente = gerente;
        modelAux = new ArrayList<Contatos>();
        listaApresentacao = new ArrayList<Contatos>();
        toaster = newToaster();
        toasterListener = new ToasterListener();
    }

    /**
     * Metodo de Configuracao do frame
     */
    public void config() {
        try {
            setTitle(Criptografia.decripto(cliente.getContatos().getNome()));
            setIconImage(getIcon());
            setContentPane(new Container());
            setSize(250, (int) dimensao.getHeight() - 40);
            setResizable(true);
            setUndecorated(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            int x = (int) (dimensao.getWidth() - 250);
            setLocation(x, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo de inicializacao dos componentes do frame
     */
    public void inicializa() {
        scrollContatos = newJScrollPane();
        modelContatos = newDefaultListModel();
        listaContatos = newJList(modelContatos);
        painelUsuario = newJPanel();
        painelStatus = newJPanel();
        listaContatos.setCellRenderer(new ContatosRender());
        scrollContatos.setViewportView(listaContatos);
        btnAdicionaContato = newJButton("imagens/adicionaContato.gif",
                "imagens/adicionaContatoDesabilitado.gif", "Adicionar", 0, 0,
                20, 20);
        btnChat = newJButton("imagens/chat.png",
                "imagens/chatDesabilitado.png", "Chat", 25, 0, 20, 20);
        btnOpenDownloads = newJButton("imagens/downloads.png",
                "imagens/downloadsDesabilitado.png", "Downloads", 50, 0, 20, 20);
        btnRemoveContato = newJButton("imagens/excluiContato.gif",
                "imagens/excluiContatoDesabilitado.gif", "Excluir", 75, 0, 20,
                20);
        painelBotoes = newJToolBar();
        adicionaPainelBotoes(new JComponent[] { btnAdicionaContato, btnChat,
                btnOpenDownloads, btnRemoveContato });
        adicionaTela(painelUsuario, 5, 0, 245, 120);
        adicionaTela(painelStatus, 5, 120, 245, 20);
        adicionaTela(painelBotoes, 5, 140, 245, 28);
        adicionaTela(scrollContatos, 5, 168, 245, 530);
        modelContatos.addElement(new Contatos(" Friends"));
        disableButtons();

    }

    private void disableButtons() {
        btnAdicionaContato.setEnabled(false);
        btnRemoveContato.setEnabled(false);
        btnChat.setEnabled(false);
    }

    /**
     * Adiciona um Array de JComponentes no frame
     * 
     * @param components
     */
    private void adicionaTela(JComponent c, Integer x, Integer y, Integer size,
            Integer alt) {
        c.setBounds(x, y, size, alt);
        add(c);
    }

    private void adicionaPainelBotoes(JComponent[] componentes) {
        for (int i = 0; i < componentes.length; i++) {
            painelBotoes.add((JButton) componentes[i]);
        }
    }

    /**
     * Instancia Um JScrollPane
     * 
     * @return
     */
    private JScrollPane newJScrollPane() {
        JScrollPane painel = new JScrollPane(
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return painel;
    }

    private Toaster newToaster() {
        Toaster t = new Toaster(new Dimension(170, 60));
        return t;
    }

    private JPanel newJPanel() {
        return new JPanel();
    }

    private JButton newJButton(String urlImagem, String urlImagemDesabilitado,
            String action, Integer x, Integer y, Integer size, Integer alt) {
        Insets ins = new Insets(0, 0, 0, 0);
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImagem);
        ImageIcon icon = new ImageIcon(res);
        icon = Util.RedimencionaImagemIcon.redimencionaImagem(icon, 20, 20,
                1500);
        JButton btn = new JButton();
        btn.setMargin(ins);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setIcon(icon);
        btn.setActionCommand(action);
        btn.setBounds(x, y, size, alt);
        res = clazz.getResource(urlImagemDesabilitado);
        icon = new ImageIcon(res);
        icon = Util.RedimencionaImagemIcon.redimencionaImagem(icon, 20, 20,
                1500);
        btn.setDisabledIcon(icon);
        return btn;
    }

    private JToolBar newJToolBar() {
        JToolBar painel = new JToolBar();
        Insets ins = new Insets(0, 0, 0, 0);
        painel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        painel.setMargin(ins);
        return painel;
    }

    private JComboBox newJComboBox() {
        JComboBox combo = new JComboBox();
        combo.setRenderer(new ComboCellRender());
        combo.setActionCommand("Estado");
        combo.setBounds(50, 120, 150, 20);
        Object[] onLine = new Object[] {
                "On Line",
                Util.RedimencionaImagemIcon.redimencionaImagem(
                        getImageIcon("imagens/online.png"), 10, 10, 1500) };
        Object[] ausente = new Object[] {
                "Ausente",
                Util.RedimencionaImagemIcon.redimencionaImagem(
                        getImageIcon("imagens/ausente.png"), 10, 10, 1500) };
        Object[] ocupado = new Object[] {
                "Ocupado",
                Util.RedimencionaImagemIcon.redimencionaImagem(
                        getImageIcon("imagens/ocupado.png"), 10, 10, 1500) };
        combo.addItem(onLine);
        combo.addItem(ausente);
        combo.addItem(ocupado);
        return combo;
    }

    /**
     * Instancia Uma DefaultaListModel
     * 
     * @return
     */
    private DefaultListModel newDefaultListModel() {
        return new DefaultListModel();
    }

    /**
     * Instancia um JList
     * 
     * @param model
     * @return
     */
    private JList newJList(DefaultListModel model) {
        JList lista = new JList(model);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return lista;
    }

    /**
     * Renderiza a tela
     */
    public void renderiza() {
        setVisible(true);
    }

    /**
     * Cria Uma imagemIcon
     * 
     * @return
     */
    private Image getIcon() {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource("imagens/serverRunning.png");
        ImageIcon icon = new ImageIcon(res);
        return icon.getImage();
    }

    /**
     * Adiciona Listeners nos componentes
     */
    public void adicionaListener() {
        listener = new FormListFriendsListener(this, gerente);
        scrollContatos.addKeyListener(listener);
        listaContatos.addMouseListener(listener);
        listaContatos.addKeyListener(listener);
        btnOpenDownloads.addActionListener(listener);
        btnOpenDownloads.addMouseListener(listener);
        btnChat.addMouseListener(listener);
        btnAdicionaContato.addMouseListener(listener);
        btnRemoveContato.addMouseListener(listener);
        addWindowListener(listener);
    }

    /**
     * set JList Contatos
     * 
     * @param listaContatos
     */
    protected void setListaContatos(JList listaContatos) {
        this.listaContatos = listaContatos;
    }

    /**
     * set DefaultListModel Contatos
     * 
     * @param modelContatos
     */
    protected void setModelContatos(DefaultListModel modelContatos) {
        this.modelContatos = modelContatos;
    }

    /**
     * adiciona Contatos na listaContatos
     * 
     * @param contato
     */
    public void carregaContatos(List<Contatos> contatos) {
        new ThreadCarregaContatos(contatos, modelContatos, listaApresentacao,
                modelAux, isListaAberta, this).start();
    }

    public void adicionaUsuario(Contatos contatos) {
        painelUsuario.setComponentOrientation(ComponentOrientation.UNKNOWN);
        lblUsuario = new JLabel();
        lblUsuario.setBounds(50, 20, 150, 100);
        lblUsuario.setVerticalAlignment(JLabel.CENTER);
        lblUsuario.setHorizontalAlignment(JLabel.CENTER);
        lblUsuario.setIcon(Util.RedimencionaImagemIcon.redimencionaImagem(
                contatos.getIconUsuario(), 60, 60, 1000));
        lblUsuario.setText(Criptografia.decripto(contatos.getNome()));
        lblUsuario.setBounds(30, 85, 100, 30);
        lblUsuario.setForeground(Color.BLUE);
        lblUsuario.setFont(new Font("verdana", Font.BOLD, 14));
        painelUsuario.add(lblUsuario);
        status = newJComboBox();
        status.addActionListener(listener);
        painelStatus.add(status);
        status.setEnabled(false);
        setVisible(true);
    }

    /**
     * remove Contato da listaContatos
     * 
     * @param contato
     */
    public void removeContato(Contatos contato) {
        ContatosComparator c = new ContatosComparator();
        if (isListaAberta) {
            Contatos contatoS = (Contatos) modelContatos.getElementAt(0);
            List<Contatos> aux = listaApresentacao;
            if (!aux.contains(contatoS)) {
                aux.add(0, contatoS);
            }
            modelContatos.clear();
            aux.remove(contato);
            Collections.sort(aux, c);
            for (Contatos contatoZ : aux) {
                modelContatos.addElement(contatoZ);
            }
            aux.remove(0);
            listaApresentacao = aux;
        } else {
            modelAux.remove(contato);
            Collections.sort(modelAux, c);
        }
        gerente.verificaInstanciaConversa(contato);
    }

    /**
     * Cria um JMenuBar
     */
    public void createMenuBar() {
        JMenuBar menuBar = newJMenuBar();
        menuBar.add(newJMenuItens("MsMundica", new JMenuItem[] {
                new JMenuItem("Minimizar"), new JMenuItem("Sair") }));
    }

    /**
     * Instancia um novo JMenu
     * 
     * @param nomeMenu
     * @return
     */
    protected JMenu newJMenu(String nomeMenu) {
        return new JMenu(nomeMenu);
    }

    /**
     * Cria um JMenu com itens
     * 
     * @param nomeMenu
     * @param items
     * @return
     */
    protected JMenu newJMenuItens(String nomeMenu, JMenuItem[] items) {
        JMenu menu = newJMenu(nomeMenu);
        FormListFriendsListener listener = new FormListFriendsListener(this,
                gerente);
        for (int i = 0; i < items.length; i++) {
            items[i].addActionListener(listener);
            menu.add(items[i]);
        }
        return menu;
    }

    /**
     * Instancia um JMenuBar
     * 
     * @return
     */
    protected JMenuBar newJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        super.setJMenuBar(menuBar);
        return menuBar;
    }

    /**
     * Cria as bordas das JList
     * 
     * @param nomeUsuario
     */
    public void criarBordaPainel(String nomeUsuario) {
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border compound = BorderFactory.createCompoundBorder(raisedbevel,
                loweredbevel);
        adicionaBordaPainelContatos(compound);
        TitledBorder titled = BorderFactory.createTitledBorder(loweredbevel,
                nomeUsuario);
        adicionaBordaPainelUsuario(titled);

    }

    private ImageIcon getImageIcon(String urlImage) {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource(urlImage);
        ImageIcon icon = new ImageIcon(res);
        return icon;
    }

    public void createTrayIcon() {
        if (trayManager == null) {
            trayManager = new TrayManagerFormListFriend(this);
            trayManager.createTrayIcon("MsMundica is active - "
                    + Criptografia.decripto(getNomeCliente()), getIcon());
            trayManager.criaMenu("Exit", true);
            trayManager.criaMenu("Abrir", true);
            trayManager.adicionaEvento();
            trayManager.addTrayIcon();
        } else {
            trayManager.addTrayIcon();
        }
    }

    /**
     * Adiciona Borda JList Contatos
     * 
     * @param border
     */
    public void adicionaBordaPainelContatos(Border border) {
        scrollContatos.setLayout(new ScrollPaneLayout());
        scrollContatos.setDoubleBuffered(false);
        scrollContatos.setBorder(border);
    }

    /**
     * Adiciona Borda JPanel Usuario
     * 
     * @param border
     */
    public void adicionaBordaPainelUsuario(TitledBorder border) {
        painelUsuario.setLayout(new BorderLayout());
        painelUsuario.setBorder(border);
    }

    public void bordaBotao(JButton button, boolean isPainted) {
        if (button.getActionCommand().equals("Adicionar")) {
            btnAdicionaContato.setBorderPainted(isPainted);
        } else if (button.getActionCommand().equals("Downloads")) {
            btnOpenDownloads.setBorderPainted(isPainted);
        } else if (button.getActionCommand().equals("Excluir")) {
            btnRemoveContato.setBorderPainted(isPainted);
        } else if (button.getActionCommand().equals("Chat")) {
            btnChat.setBorderPainted(isPainted);
        }
    }

    /**
     * set JScrollPane painelContatos
     * 
     * @param painelContatos
     */
    protected void setPainelContatos(JScrollPane painelContatos) {
        this.scrollContatos = painelContatos;
    }

    /**
     * set o Cliente para antes de iniciar o form
     * 
     * @param cliente
     */
    public void setCliente(IMensageiroCliente cliente) {
        this.cliente = cliente;

    }

    public JList getListaContatos() {
        return listaContatos;
    }

    public DefaultListModel getContatos() {
        return modelContatos;
    }

    public void removeCliente() {
        try {
            cliente.comunicaSaida();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void buscaContatos() {
        try {
            cliente.buscaContatos(cliente);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void IsListaAberta(boolean isListaAberta, Contatos contato) {
        if (isListaAberta) {
            modelAux.set(0, contato);
            modelContatos.clear();
            for (int i = 0; i < modelAux.size(); i++) {
                modelContatos.addElement(modelAux.get(i));
            }
            modelAux = new ArrayList<Contatos>();
        } else {
            for (int i = 0; i < modelContatos.getSize(); i++) {
                modelAux.add((Contatos) modelContatos.getElementAt(i));
            }
            modelContatos.clear();
            modelContatos.addElement(modelAux.get(0));
        }
        this.isListaAberta = isListaAberta;
    }

    public IMensageiroCliente getCliente() {
        return this.cliente;
    }

    public String getNomeCliente() {
        try {
            return getCliente().getContatos().getNome();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public JLabel getLblNome() {
        return lblUsuario;
    }

    public boolean getIsListaAberta() {
        return isListaAberta;
    }

    public JComboBox getComboEstado() {
        return status;
    }

    public JLabel getLblUsuario() {
        return lblUsuario;
    }

    public void contatoConectou(Contatos contatos) {
        toaster.adicionaToaster(toasterListener, Criptografia.decripto(contatos
                .getNome()), this.getBackground(), this.getBackground(),
                "is On Line", Util.RedimencionaImagemIcon.redimencionaImagem(
                        contatos.getIconContato(), 50, 50, 500), Criptografia
                        .decripto(contatos.getLogin()));
        if (isListaAberta) {
            listaApresentacao.add(contatos);
            Collections.sort(listaApresentacao, new ContatosComparator());
            modelContatos.clear();
            Contatos primeiro = new Contatos(" Friends");
            modelContatos.addElement(primeiro);
            for (Contatos contato : listaApresentacao) {
                modelContatos.addElement(contato);
            }
        } else {
            modelAux.add(contatos);
            Collections.sort(modelAux, new ContatosComparator());
        }
    }

    public void activeToaster(Mensagem mensagem) {
        Contatos contato = new Contatos();
        contato.setLogin(Criptografia.decripto(mensagem.getUsuarioEnvia()));
        int posicao = getContatos().indexOf(contato);
        if (posicao != -1) {
            contato = (Contatos) getContatos().getElementAt(posicao);
            ImageIcon image = Util.RedimencionaImagemIcon.redimencionaImagem(
                    contato.getIconContato(), 50, 50, 500);
            toaster.adicionaToaster(toasterListener, Criptografia
                    .decripto(mensagem.getNomeEnvia()), this.getBackground(),
                    this.getBackground(), Criptografia.decripto(mensagem
                            .getMensagem()), image, Criptografia
                            .decripto(mensagem.getUsuarioEnvia()));
        } else {
            //TODO
        }

    }
}