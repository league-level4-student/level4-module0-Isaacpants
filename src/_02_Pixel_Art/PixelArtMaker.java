package _02_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//import _05_Serialization.SaveData;

public class PixelArtMaker implements MouseListener, Serializable, ActionListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	private final String saveFileLoc = System.getProperty( "user.dir" ) +  "src" + File.separator + "_02_Pixel_Art"+ File.separator + "saveData.dat";
	JLabel jl = new JLabel();
	private JButton jb;
	ColorSelectionPanel csp;
	public void saveState(GridPanel grid) {
System.out.println("Saving File to:" + saveFileLoc);
		try (FileOutputStream fos = new FileOutputStream(new File(saveFileLoc));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(grid);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		
	}public GridPanel loadState() {
		System.out.println("Loading from:" + saveFileLoc);
		try (FileInputStream fis = new FileInputStream(new File(saveFileLoc));
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (GridPanel) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		jb = new JButton("Save");
		jb.addMouseListener(this);
		
		
		window.add(gip);
		window.add(jl);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = loadState();
		if(gp==null) {
		gp = new GridPanel(w, h, r, c);
		}
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		gp.repaint();
		gp.addMouseListener(this);
		
		window.add(jb);
		window.pack();
		loadState();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==jb) {
			System.out.println("works");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		saveState(gp);
		
		
	}
}
