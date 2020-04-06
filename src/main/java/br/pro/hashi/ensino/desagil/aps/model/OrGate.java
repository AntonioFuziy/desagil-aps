package br.pro.hashi.ensino.desagil.aps.model;

<<<<<<< HEAD
public class OrGate extends Gate{
=======
public class OrGate extends Gate {
>>>>>>> 00ed2f074fab07e64bf1ff0496c6d5bff8f2b7d1
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;

<<<<<<< HEAD
    public OrGate(){
=======
    public OrGate() {
>>>>>>> 00ed2f074fab07e64bf1ff0496c6d5bff8f2b7d1
        super("OR", 2);

        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();
    }

<<<<<<< HEAD
    @Override
    public boolean read(){
        return nand3.read();
=======

    @Override
    public boolean read() {

        return (nand3.read());
>>>>>>> 00ed2f074fab07e64bf1ff0496c6d5bff8f2b7d1
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        if (inputIndex == 0) {
            nand1.connect(0, emitter);
            nand1.connect(1, emitter);
        }
        if (inputIndex == 1) {
            nand2.connect(0, emitter);
            nand2.connect(1, emitter);
        }
        nand3.connect(0,nand1);
        nand3.connect(1,nand2);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 00ed2f074fab07e64bf1ff0496c6d5bff8f2b7d1
