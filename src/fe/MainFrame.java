package fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;

import abs.AFrame;
import dal.CustomerDal;
import type.CustomerContract;

public class MainFrame extends AFrame {

	public MainFrame() {
		initFrame("Musteri Ekle", initPanel());
	}
	
	@Override
	public JPanel initPanel() {
		JPanel buttonsJPanel = new JPanel(new GridLayout(5,2));
		JLabel searchJLabel = new JLabel("Kisi ara:", JLabel.RIGHT);
		buttonsJPanel.add(searchJLabel);
		JTextField searchField = new JTextField(10);
		buttonsJPanel.add(searchField);
		JPanel panel = new JPanel(new BorderLayout());
		JLabel nameJLabel = new JLabel("Adi:", JLabel.RIGHT);
		buttonsJPanel.add(nameJLabel);
		JTextField nameField = new JTextField(10);
		buttonsJPanel.add(nameField);
		
		JLabel surnameJLabel = new JLabel("Soyadi:", JLabel.RIGHT);
		buttonsJPanel.add(surnameJLabel);
		JTextField surnameField = new JTextField(10);
		buttonsJPanel.add(surnameField);
		
		JButton saveButton = new JButton("Kaydet");
		buttonsJPanel.add(saveButton);
		JButton updateButton = new JButton("Duzenle");
		buttonsJPanel.add(updateButton);
		JButton deleteButton = new JButton("Sil");
		buttonsJPanel.add(deleteButton);
		JButton refreshButton = new JButton("Yenile");
		buttonsJPanel.add(refreshButton);
		
		JList list = new JList(new CustomerDal().getList().toArray());
		JScrollPane pane = 	new JScrollPane(list);
		
		list.setSelectedIndex(0);
		
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(nameField,"liste yenilendi.");
				list.setListData(new CustomerDal().getList().toArray());
				list.setSelectedIndex(0);
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerContract contract = new CustomerContract();
				
				contract.setName(nameField.getText());
				contract.setSurname(surnameField.getText());
				
				new CustomerDal().insert(contract);
				JOptionPane.showMessageDialog(nameField, contract.getName() 
						+ " " + contract.getSurname() + " adli kisi eklendi");
				list.setListData(new CustomerDal().getList().toArray());
				list.setSelectedIndex(0);;
			}
		});
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e ) {
				
				CustomerContract contract = (CustomerContract) list.getSelectedValue();
				
				if(contract!=null) {
					CustomerContract contDal = new CustomerDal().getById(contract.getId());
					nameField.setText(contDal.getName());	
					surnameField.setText(contDal.getSurname());
					
				}else {
					list.setSelectedIndex(0);
				}
				
				
				
			}
		});
		
		searchField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				list.setListData(new CustomerDal().getSearch(searchField.getText()).toArray());
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerContract cast = (CustomerContract) list.getSelectedValue();
				CustomerContract contract = new CustomerContract();
				
				contract.setId(cast.getId()); 
				contract.setName(nameField.getText());
				contract.setSurname(surnameField.getText());
				
				new CustomerDal().update(contract);
				JOptionPane.showMessageDialog(nameField, contract.getName()
						+" "+ contract.getSurname() + " adli kisi duzenlendi");
				list.setListData(new CustomerDal().getList().toArray());
			}
		});
		
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerContract contract = (CustomerContract) list.getSelectedValue();
				
				new CustomerDal().delete(contract);
				JOptionPane.showMessageDialog(nameField, contract.getName()
						+" "+ contract.getSurname() +" adli kisi silinmistir.");
				list.setListData(new CustomerDal().getList().toArray());
			}
		});
		
		
		panel.add(buttonsJPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		
		return panel;
	}

}
