class Clause {
    int a;
    int b;
    public Clause(int a, int b){
        this.a = a;
        this.b = b;
    }
    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
    public boolean evaluate(boolean a1, boolean b1) {
        if(a < 0) {
            a1 = !a1;
        } 
        if(b < 0) {
            b1 = !b1;
        }
         return a1 || b1;
    }
}