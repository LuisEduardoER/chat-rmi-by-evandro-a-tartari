package forms;

import gerenteDeTelas.Gerente;
import interfaces.IMensageiroCliente;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.rmi.RemoteException;

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
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import acao.FormListFriendsListener;
import java.awt.Toolkit;
import contatos.Contatos;
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
	private JScrollPane painelContatos;
	private DefaultListModel modelUsuario;
	private DefaultListModel modelContatos;
	private JList listaUsuario;
	private JList listaContatos;
	private IMensageiroCliente cliente;
	private Gerente gerente;

	public FormListFriends(Gerente gerente) {
		this.gerente = gerente;
	}

	/**
	 * Metodo de Configuracao do frame
	 */
	public void config() {
		try {
			setTitle(cliente.getContatos().getNome());
			setIconImage(getIcon());
			Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
			setSize(250, (int) dimensao.getHeight() - 30);
			setResizable(true);
			setUndecorated(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			int x = (int) (dimensao.getWidth() - 250);
			setLocation(x, 0);
			Container c = this.getContentPane();
			GridBagConstraints cons = new GridBagConstraints();
			GridBagLayout layout = new GridBagLayout();
			c.setLayout(layout);
			cons.anchor = GridBagConstraints.SOUTHEAST;
			cons.fill = GridBagConstraints.BOTH;
			cons.gridx = 0;
			cons.gridy = 1;
			cons.weighty = 0.90;
			cons.weightx = 1;
			c.add(painelContatos, cons);
			cons.gridx = 0;
			cons.gridy = 0;
			cons.weighty = 0.10;
			c.add(listaUsuario, cons);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo de inicializacao dos componentes do frame
	 */
	public void inicializa() {
		painelContatos = newJScrollPane();
		modelUsuario = newDefaultListModel();
		modelContatos = newDefaultListModel();
		listaUsuario = newJList(modelUsuario);
		listaUsuario.setEnabled(false);
		listaContatos = newJList(modelContatos);
		painelContatos.setViewportView(listaContatos);
		adiciona(new JComponent[] { listaUsuario, painelContatos });
	}

	/**
	 * Adiciona um Array de JComponentes no frame
	 * 
	 * @param components
	 */
	private void adiciona(JComponent[] components) {
		for (int i = 0; i < components.length; i++) {
			add(components[i]);
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
		lista.setCellRenderer(new ContatosRender());
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
		FormListFriendsListener listener = new FormListFriendsListener(this,
				gerente);
		painelContatos.addKeyListener(listener);
		listaUsuario.addKeyListener(listener);
		listaUsuario.addMouseListener(listener);
		listaContatos.addMouseListener(listener);
		listaContatos.addKeyListener(listener);
		addWindowListener(listener);
	}

	/**
	 * set JList Usuario
	 * 
	 * @param listaUsuario
	 */
	protected void setListaUsuario(JList listaUsuario) {
		this.listaUsuario = listaUsuario;
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
	 * set DefaultListModel Usuario
	 * 
	 * @param modelUsuario
	 */
	protected void setModelUsuario(DefaultListModel modelUsuario) {
		this.modelUsuario = modelUsuario;
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
	 * adiciona Usuario na listaUsuario
	 * 
	 * @param contato
	 */
	public void adicinalUsuario(Contatos contato) {
		modelUsuario.addElement(contato);
	}

	/**
	 * adiciona Contato na listaContatos
	 * 
	 * @param contato
	 */
	public void adicionaContato(Contatos contato) {
		modelContatos.addElement(contato);
	}

	/**
	 * remove Contato da listaContatos
	 * 
	 * @param obj
	 */
	public void removeContato(Contatos obj) {
		modelContatos.removeElement(obj);
		listaContatos.setModel(modelContatos);
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

	/**
	 * Adiciona Borda JList Contatos
	 * 
	 * @param border
	 */
	public void adicionaBordaPainelContatos(Border border) {
		painelContatos.setLayout(new ScrollPaneLayout());
		painelContatos.setDoubleBuffered(false);
		painelContatos.setBorder(border);
	}

	/**
	 * Adiciona Borda JList Usuario
	 * 
	 * @param border
	 */
	public void adicionaBordaPainelUsuario(TitledBorder border) {
		listaUsuario.setLayout(new GridLayout(1, 1));
		listaUsuario.setDoubleBuffered(false);
		listaUsuario.setBorder(border);
	}

	/**
	 * set JScrollPane painelContatos
	 * 
	 * @param painelContatos
	 */
	protected void setPainelContatos(JScrollPane painelContatos) {
		this.painelContatos = painelContatos;
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
	
	public DefaultListModel getContatos(){
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
}