package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener{
    private Light light;
    private Color color;
    private final Image image;
    private final static int height = 300;
    private final static int width = 600;


    private final JCheckBox inputCheck;
    private final JCheckBox inputCheck2;
    private final JCheckBox outputCheck;
    private final Switch switch1;
    private final Switch switch2;

    public GateView(Gate gates){
        super(width, height);

        this.light = new Light(255,0,0);

        inputCheck = new JCheckBox("Entrada 1");
        inputCheck2 = new JCheckBox("Entrada 2");
        outputCheck = new JCheckBox("Saída");
        switch1 = new Switch();
        switch2 = new Switch();


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(20,20);
        add(inputCheck,15,height/2,20,20);
        if (gates.getInputSize()>1) {
            add(inputCheck2,15,height/2 +20,20,20);
        }

        add(outputCheck,15,height/2 +40,20,20);

        gates.connect(0, switch1);
        if (gates.getInputSize()>1) {
            gates.connect(1, switch2);
        }
        color = light.getColor();

        inputCheck.addActionListener(this);
        if (gates.getInputSize()>1) {
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

        // Se o clique foi dentro do quadrado colorido...
        if (x >= 210 && x < 235 && y >= 311 && y < 336) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, color);

            // ...e chamamos repaint para atualizar a tela.
            update();
        }
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

        int img_height=200;
        int img_width=500;
        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, (width/2)-img_width/2, (height/2)-img_height/2, img_width,img_height, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        //g.fillRect(210, 311, width/2, 200);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
