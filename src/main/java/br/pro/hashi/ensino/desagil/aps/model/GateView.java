package br.pro.hashi.ensino.desagil.aps.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class GateView extends JPanel implements ActionListener{
    private final Gate gates;

    private final JCheckBox inputCheck;
    private final JCheckBox inputCheck2;
    private final JCheckBox outputCheck;
    private final Switch switch1;
    private final Switch switch2;

    public GateView(Gate gates){
        this.gates = gates;

        inputCheck = new JCheckBox("Entrada 1");
        inputCheck2 = new JCheckBox("Entrada 2");
        outputCheck = new JCheckBox("Saída");
        switch1 = new Switch();
        switch2 = new Switch();


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(inputCheck);
        add(inputCheck2);
        add(outputCheck);

        gates.connect(0, switch1);
        gates.connect(0, switch2);


        outputCheck.setEnabled(false);

        update();
    }

    private void update(){
        if (inputCheck.isSelected() && inputCheck2.isSelected()){
            switch1.turnOn();
            switch2.turnOn();
        } else if (inputCheck.isSelected()){
            switch1.turnOn();
            switch2.turnOff();
        } else if (inputCheck2.isSelected()){
            switch1.turnOff();
            switch2.turnOn();
        } else {
            switch1.turnOff();
            switch2.turnOff();
        }
        outputCheck.setSelected(this.gates.read());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}
