package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.lang.Math;

public class GateView extends FixedPanel implements ActionListener, MouseListener{
    private final Gate gates;
    private Light light;
    private Color color;
    private final Image image;
    private final static int height = 200;
    private final static int width = 300;


    private final JCheckBox inputCheck;
    private final JCheckBox inputCheck2;
    private final JCheckBox outputCheck;
    private final Switch switch1;
    private final Switch switch2;

    public GateView(Gate gates){
        super(width, height);

        this.gates = gates;
        this.light = new Light(255,0,0);

        inputCheck = new JCheckBox("Entrada 1");
        inputCheck2 = new JCheckBox("Entrada 2");
        outputCheck = new JCheckBox("Saída");
        switch1 = new Switch();
        switch2 = new Switch();


        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(20,20);
        if (gates.getInputSize() > 1) {
            add(inputCheck,5,height/2 - 30,20,20);
            add(inputCheck2,5,height/3 + 48,20,20);
        }else{
            add(inputCheck,5,height/2 - 8,20,20);
        }

        //add(outputCheck,275,height/2 - 10,20,20);

        gates.connect(0, switch1);
        if (gates.getInputSize() > 1) {
            gates.connect(1, switch2);
        }

        this.light.connect(0, gates);

        color = light.getColor();


        inputCheck.addActionListener(this);
        if (gates.getInputSize() > 1) {
            inputCheck2.addActionListener(this);
        }
        outputCheck.setEnabled(false);

        //color = Color.BLACK;

        // Usamos esse carregamento nos Desafios, vocês lembram?
        String name = gates.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        addMouseListener(this);

        update();
    }

    private void update() {
        System.out.println("Okay Boomer!!!");

            if (inputCheck.isSelected() && inputCheck2.isSelected()) {
                switch1.turnOn();
                switch2.turnOn();
            } else if (inputCheck.isSelected()) {
                switch1.turnOn();
                switch2.turnOff();
            } else if (inputCheck2.isSelected()) {
                switch1.turnOff();
                switch2.turnOn();
            } else {
                switch1.turnOff();
                switch2.turnOff();
            }
            outputCheck.setSelected(this.gates.read());
            color=light.getColor();
            repaint();
        /*if (this.gates.read()) {
            //Círculo colorido
            color = light.getColor();
        }
        else {
            //Círculo preto
            color = Color.BLACK;

        }*/
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();
        if (x >=255 && x <= 295 && y >= height/2-10-20 && y <= height/2-10+20 && Math.sqrt(Math.abs((x-275))*Math.abs((x-275)) + Math.abs((y-height/2-10))*Math.abs((y-height/2-10)))<20 ) {

            // ...então abrimos a janela seletora de cor...
            light.setColor(JColorChooser.showDialog(this, null, color));


            // ...e chamamos repaint para atualizar a tela.
        }
        update();

    }

    @Override
    public void mousePressed(MouseEvent event) {
    }
    @Override
    public void mouseReleased(MouseEvent event) {
    }
    @Override
    public void mouseEntered(MouseEvent event) {
    }
    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void paintComponent(Graphics g) {

        int img_height=100;
        int img_width=250;
        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, (width/2)-img_width/2, (height/2)-img_height/2, img_width,img_height, this);

        g.setColor(color);
        g.fillOval(275, height/2-10, 20, 20);

        //g.fillRect(210, 311, width/2, 200);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
