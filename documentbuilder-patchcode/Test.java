class Test {
    public boolean getFeature(String feature) {
        return true;
    }

    public void thing() {
        if(!this.getFeature("http://apache.org/xml/features/disallow-doctype-decl")) {
            System.out.println("Insecure!");
            new Exception().printStackTrace(System.out);
        }
        System.out.println("What!");
        
    }
}
