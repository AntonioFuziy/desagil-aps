package br.pro.hashi.ensino.desagil.aps.model;

<<<<<<< HEAD
public class AndGate extends Gate{
    private final NandGate nand1;
    private final NandGate nand2;

    public AndGate(){
=======
public class AndGate extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;

    protected AndGate() {
>>>>>>> 00ed2f074fab07e64bf1ff0496c6d5bff8f2b7d1
        super("AND", 2);

        nand1 = new NandGate();
        nand2 = new NandGate();
    }

    @Override
<<<<<<< HEAD
    public boolean read(){
        return nand2.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter){
        if (inputIndex < 0 || inputIndex > 1){
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nand1.connect(inputIndex, emitter);
=======
    public boolean read() {
        return (nand2.read());
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }
        nand1.connect(inputIndex,emitter);
>>>>>>> 00ed2f074fab07e64bf1ff0496c6d5bff8f2b7d1
        nand2.connect(0, nand1);
        nand2.connect(1, nand1);
    }
}
