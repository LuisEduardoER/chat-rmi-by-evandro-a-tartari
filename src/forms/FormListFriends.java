package forms;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.ScrollPaneLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.jvnet.substance.SubstanceDefaultLookAndFeel;

import acao.FormListFriendsListener;
import contatos.Contatos;
import contatos.render.ContatosRender;

public class FormListFriends extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -4098352435404830798L;
    private JScrollPane painelContatos;
    private DefaultListModel modelUsuario;
    private DefaultListModel modelContatos;
    private JList listaUsuario;
    private JList listaContatos;
    private SubstanceDefaultLookAndFeel substance;

    private void config() {
        setTitle("Titulo a colocar");
        setIconImage(getIcon());
        setSize(150, 300);
        setResizable(true);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container c = this.getContentPane();
        GridBagConstraints cons = new GridBagConstraints();
        GridBagLayout layout = new GridBagLayout();
        c.setLayout(layout);
        cons.anchor = GridBagConstraints.SOUTHEAST;
        cons.fill = GridBagConstraints.BOTH;
        cons.gridx = 0;
        cons.gridy = 1;
        cons.weighty = 0.75;
        cons.weightx = 1;
        c.add(painelContatos, cons);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weighty = 0.25;
        c.add(listaUsuario, cons);
    }

    private void inicializa() {
        painelContatos = newJScrollPane();
        modelUsuario = newDefaultListModel();
        modelContatos = newDefaultListModel();
        listaUsuario = newJList(modelUsuario);
        listaUsuario.setEnabled(false);
        listaContatos = newJList(modelContatos);
        painelContatos.setViewportView(listaContatos);
        adiciona(new JComponent[] { listaUsuario, painelContatos });
        teste();
    }

    private void adiciona(JComponent[] components) {
        for (int i = 0; i < components.length; i++) {
            add(components[i]);
        }
    }

    private JScrollPane newJScrollPane() {
        JScrollPane painel = new JScrollPane(
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return painel;
    }

    /**
     * To remove
     */
    private void teste() {
        Contatos contato = new Contatos();
        contato.setNome("Teste01");
        contato.setIcon(new ImageIcon(this.getClass().getClassLoader()
                .getResource("imagens/serverRunning.png")));
        adicinalUsuario(contato);
        Contatos contato2 = new Contatos();
        contato2.setNome("Teste02");
        contato2.setIcon(new ImageIcon(this.getClass().getClassLoader()
                .getResource("imagens/serverNotRunning.png")));
        adicionaContato(contato2);
    }

    private DefaultListModel newDefaultListModel() {
        return new DefaultListModel();
    }

    private JList newJList(DefaultListModel model) {
        JList lista = new JList(model);
        lista.setCellRenderer(new ContatosRender());
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return lista;
    }

    private void renderiza() {
        setVisible(true);
    }

    private Image getIcon() {
        ClassLoader clazz = this.getClass().getClassLoader();
        URL res = clazz.getResource("imagens/serverRunning.png");
        ImageIcon icon = new ImageIcon(res);
        return icon.getImage();
    }

    public void adicionaListener() {
        FormListFriendsListener listener = new FormListFriendsListener(this);
        painelContatos.addKeyListener(listener);
        listaUsuario.addKeyListener(listener);
        listaUsuario.addMouseListener(listener);
        listaContatos.addMouseListener(listener);
        listaContatos.addKeyListener(listener);
    }

    protected void setListaUsuario(JList listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    protected void setListaContatos(JList listaContatos) {
        this.listaContatos = listaContatos;
    }

    protected void setModelUsuario(DefaultListModel modelUsuario) {
        this.modelUsuario = modelUsuario;
    }

    protected void setModelContatos(DefaultListModel modelContatos) {
        this.modelContatos = modelContatos;
    }

    public void adicinalUsuario(Contatos contato) {
        modelUsuario.addElement(contato);
    }

    public void adicionaContato(Contatos contato) {
        modelContatos.addElement(contato);
    }

    public void removeContato(Contatos obj) {
        modelContatos.removeElement(obj);
        listaContatos.setModel(modelContatos);
    }

    public void createMenuBar() {
        JMenuBar menuBar = newJMenuBar();
        menuBar.add(newJMenuItens("MsMundica", new JMenuItem[] {
                new JMenuItem("Minimizar"), new JMenuItem("Sair") }));
    }

    protected JMenu newJMenu(String nomeMenu) {
        return new JMenu(nomeMenu);
    }

    protected JMenu newJMenuItens(String nomeMenu, JMenuItem[] items) {
        JMenu menu = newJMenu(nomeMenu);
        FormListFriendsListener listener = new FormListFriendsListener(this);
        for (int i = 0; i < items.length; i++) {
            items[i].addActionListener(listener);
            menu.add(items[i]);
        }
        return menu;
    }

    protected JMenuBar newJMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        super.setJMenuBar(menuBar);
        return menuBar;
    }

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

    public void adicionaBordaPainelContatos(Border border) {
        painelContatos.setLayout(new ScrollPaneLayout());
        painelContatos.setDoubleBuffered(false);
        painelContatos.setBorder(border);
    }

    public void adicionaBordaPainelUsuario(TitledBorder border) {
        listaUsuario.setLayout(new GridLayout(1, 1));
        listaUsuario.setDoubleBuffered(false);
        listaUsuario.setBorder(border);
    }

    public static void main(String[] args) {
        FormListFriends list = new FormListFriends();
        list.setLookAndFeelThisForm();
        list.inicializa();
        list.config();
        list.createMenuBar();
        list.criarBordaPainel("Teste01");
        list.adicionaListener();
        list.renderiza();
    }

    @Deprecated
    private void setLookAndFeelThisForm() {
        try {
            substance = new SubstanceDefaultLookAndFeel();
            UIManager.setLookAndFeel((LookAndFeel) substance);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }

    protected void setPainelContatos(JScrollPane painelContatos) {
        this.painelContatos = painelContatos;
    }

}