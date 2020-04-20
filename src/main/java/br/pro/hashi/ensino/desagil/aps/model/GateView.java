package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener{
    private final Gate gates;
    private Light light;
    private Color color;
    private final Image image;
    private final int height = 300;
    private final int width = 400;


    private final JCheckBox inputCheck;
    private final JCheckBox inputCheck2;
    private final JCheckBox outputCheck;
    private final Switch switch1;
    private final Switch switch2;

    public GateView(Gate gates){
        super(400, 500);

        this.gates = gates;
        this.light = new Light(255,0,0);

        inputCheck = new JCheckBox("Entrada 1");
        inputCheck2 = new JCheckBox("Entrada 2");
        outputCheck = new JCheckBox("Saída");
        switch1 = new Switch();
        switch2 = new Switch();


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(inputCheck);
        if (gates.getInputSize()>1) {
            add(inputCheck2);
        }
        add(outputCheck);

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

        color = Color.BLACK;

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
        if (this.gates.read()) {
            //Círculo colorido
            color = light.getColor();
        }
        else {
            //Círculo preto
            color = Color.BLACK;

        }
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
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 0, 100, 200, 100, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        g.fillRect(210, 311, width/2, 100);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
