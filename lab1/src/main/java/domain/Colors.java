package domain;

public enum Colors {
    RED("red"),
    BLUE("blue");//DONOT ADD OTHER COLORS

    private String text;

    Colors(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Colors setText(String text) {
        this.text = text;
        return this;
    }

    public Colors getOposite(){
        if (this==RED) {
            return BLUE;
        } else {
            return RED;
        }
    }


}
