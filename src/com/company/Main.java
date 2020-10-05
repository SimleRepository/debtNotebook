package com.company;

//import Debt.Debt;
import javax.swing.*;
public class Main {

    public static void main(String[] args) {
	// write your code here

        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,200,100, 40);//x axis, y axis, width, height
        JButton c=new JButton("click");//creating instance of JButton
        c.setBounds(150,200,100, 40);//x axis, y axis, width, height
        f.add(b);//adding button in JFrame
        f.add(c);

        JTextField t1,t2;
        t1=new JTextField("Welcome to Javatpoint.");
        t1.setBounds(150,100, 200,30);
        t2=new JTextField("AWT Tutorial");
        t2.setBounds(50,150, 200,30);
        f.add(t1); f.add(t2);


        f.setSize(500,700);//400 width and 500 height
        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}