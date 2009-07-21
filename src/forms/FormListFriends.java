package forms;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import contatos.render.ContatosRender;

public class FormListFriends extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -4098352435404830798L;
    private JPanel painelUsuario;
    private JPanel painelContatos;
    private DefaultListModel modelUsuario;
    private DefaultListModel modelContatos;
    private JList listaUsuario;
    private JList listaContatos;

    private void config() {
        setTitle("Titulo a colocar");
        setIconImage(getIcon());
        setSize(150, 300);
        setResizable(true);
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
        c.add(painelUsuario, cons);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.weighty = 0.25;
        c.add(painelContatos, cons);
    }

    private void inicializa() {
        setPainelUsuario(newJPanel());
        setPainelContatos(newJPanel());
        setModelUsuario(newDefaultListModel());
        setModelContatos(newDefaultListModel());
        setListaUsuario(newJList(modelUsuario));
        addPainel(painelUsuario, listaUsuario);
        setListaContatos(newJList(modelContatos));
        addPainel(painelContatos, listaContatos);

    }

    private JPanel newJPanel() {
        JPanel painel = new JPanel();
        add(painel);
        return painel;
    }

    private void addPainel(JPanel painel, JComponent c) {
        painel.add(c);
    }

    private DefaultListModel newDefaultListModel() {
        return new DefaultListModel();
    }

    private JList newJList(DefaultListModel model) {
        JList lista = new JList(model);
        lista.setCellRenderer(new ContatosRender());
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

    public static void main(String[] args) {
        FormListFriends list = new FormListFriends();
        list.inicializa();
        list.config();
        list.renderiza();
    }

    protected void setListaUsuario(JList listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    protected void setListaContatos(JList listaContatos) {
        this.listaContatos = listaContatos;
    }

    protected void setPainelContatos(JPanel painelContatos) {
        this.painelContatos = painelContatos;
    }

    protected void setModelUsuario(DefaultListModel modelUsuario) {
        this.modelUsuario = modelUsuario;
    }

    protected void setModelContatos(DefaultListModel modelContatos) {
        this.modelContatos = modelContatos;
    }

    protected void setPainelUsuario(JPanel painelUsuario) {
        this.painelUsuario = painelUsuario;
    }

    public void adicinalUsuario(Object obj) {
        modelUsuario.addElement(obj);
    }

    public void adicionaContato(Object obj) {
        modelContatos.addElement(obj);
    }

    public void removeContato(Object obj) {
        modelContatos.removeElement(obj);
        listaContatos.setModel(modelContatos);
    }

}