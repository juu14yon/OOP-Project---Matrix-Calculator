public class RestoreMatrix {
    private int[] addA;
    private int[] addB;
    private int addDimR;
    private int addBimC;

    private int[] multiA;
    private int[] multiB;
    private int multiADimR;
    private int multiABimC;
    private int multiBDimC;

    private int[] trans;
    private int transDimR;
    private int transDimC;

    private int[] inv;
    private int invDim;

    private int[] det;
    private int detDim;

    private int[] gauss;
    private int gaussDimR;
    private int gaussDimC;

    String operation;

    public RestoreMatrix(String operation) {
        this.operation = operation;
    }

    public int[] getSingleEntries(){
        switch (operation){
            case "det": return getDet();
            case "inv": return getInv();
            case "trans": return getTrans();
            case "gauss": return getGauss();
            default: return new int[1];
        }
    }

    public void setSingleEntries(){
        switch (operation){

        }
    }

    public int[] getAddA() {
        return addA;
    }

    public void setAddA(int[] addA) {
        this.addA = addA;
    }

    public int[] getAddB() {
        return addB;
    }

    public void setAddB(int[] addB) {
        this.addB = addB;
    }

    public int getAddDimR() {
        return addDimR;
    }

    public void setAddDimR(int addDimR) {
        this.addDimR = addDimR;
    }

    public int getAddBimC() {
        return addBimC;
    }

    public void setAddBimC(int addBimC) {
        this.addBimC = addBimC;
    }

    public int[] getMultiA() {
        return multiA;
    }

    public void setMultiA(int[] multiA) {
        this.multiA = multiA;
    }

    public int[] getMultiB() {
        return multiB;
    }

    public void setMultiB(int[] multiB) {
        this.multiB = multiB;
    }

    public int getMultiADimR() {
        return multiADimR;
    }

    public void setMultiADimR(int multiADimR) {
        this.multiADimR = multiADimR;
    }

    public int getMultiABimC() {
        return multiABimC;
    }

    public void setMultiABimC(int multiABimC) {
        this.multiABimC = multiABimC;
    }

    public int getMultiBDimC() {
        return multiBDimC;
    }

    public void setMultiBDimC(int multiBDimC) {
        this.multiBDimC = multiBDimC;
    }

    public int[] getTrans() {
        return trans;
    }

    public void setTrans(int[] trans) {
        this.trans = trans;
    }

    public int getTransDimR() {
        return transDimR;
    }

    public void setTransDimR(int transDimR) {
        this.transDimR = transDimR;
    }

    public int getTransDimC() {
        return transDimC;
    }

    public void setTransDimC(int transDimC) {
        this.transDimC = transDimC;
    }

    public int[] getInv() {
        return inv;
    }

    public void setInv(int[] inv) {
        this.inv = inv;
    }

    public int getInvDim() {
        return invDim;
    }

    public void setInvDim(int invDim) {
        this.invDim = invDim;
    }

    public int[] getDet() {
        return det;
    }

    public void setDet(int[] det) {
        this.det = det;
    }

    public int getDetDim() {
        return detDim;
    }

    public void setDetDim(int detDim) {
        this.detDim = detDim;
    }

    public int[] getGauss() {
        return gauss;
    }

    public void setGauss(int[] gauss) {
        this.gauss = gauss;
    }

    public int getGaussDimR() {
        return gaussDimR;
    }

    public void setGaussDimR(int gaussDimR) {
        this.gaussDimR = gaussDimR;
    }

    public int getGaussDimC() {
        return gaussDimC;
    }

    public void setGaussDimC(int gaussDimC) {
        this.gaussDimC = gaussDimC;
    }
}
