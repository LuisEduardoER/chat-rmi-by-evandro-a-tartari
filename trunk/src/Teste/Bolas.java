package Teste;

public class Bolas {
    private Double id;
    private Double b01;
    private Double b02;
    private Double b03;
    private Double b04;
    private Double b05;
    private Double b06;
    private Double b07;
    private Double b08;
    private Double b09;
    private Double b10;
    private Double b11;
    private Double b12;
    private Double b13;
    private Double b14;
    private Double b15;

    public Bolas(Integer id, String[] lines) {
        setId(id.doubleValue());
        setB01(Double.parseDouble(lines[0]));
        setB02(Double.parseDouble(lines[1]));
        setB03(Double.parseDouble(lines[2]));
        setB04(Double.parseDouble(lines[3]));
        setB05(Double.parseDouble(lines[4]));
        setB06(Double.parseDouble(lines[5]));
        setB07(Double.parseDouble(lines[6]));
        setB08(Double.parseDouble(lines[7]));
        setB09(Double.parseDouble(lines[8]));
        setB10(Double.parseDouble(lines[9]));
        setB11(Double.parseDouble(lines[10]));
        setB12(Double.parseDouble(lines[11]));
        setB13(Double.parseDouble(lines[12]));
        setB14(Double.parseDouble(lines[13]));
        setB15(Double.parseDouble(lines[14]));
    }

    public Double getB01() {
        return b01;
    }

    public void setB01(Double b01) {
        this.b01 = b01;
    }

    public Double getB02() {
        return b02;
    }

    public void setB02(Double b02) {
        this.b02 = b02;
    }

    public Double getB03() {
        return b03;
    }

    public void setB03(Double b03) {
        this.b03 = b03;
    }

    public Double getB04() {
        return b04;
    }

    public void setB04(Double b04) {
        this.b04 = b04;
    }

    public Double getB05() {
        return b05;
    }

    public void setB05(Double b05) {
        this.b05 = b05;
    }

    public Double getB06() {
        return b06;
    }

    public void setB06(Double b06) {
        this.b06 = b06;
    }

    public Double getB07() {
        return b07;
    }

    public void setB07(Double b07) {
        this.b07 = b07;
    }

    public Double getB08() {
        return b08;
    }

    public void setB08(Double b08) {
        this.b08 = b08;
    }

    public Double getB09() {
        return b09;
    }

    public void setB09(Double b09) {
        this.b09 = b09;
    }

    public Double getB10() {
        return b10;
    }

    public void setB10(Double b10) {
        this.b10 = b10;
    }

    public Double getB11() {
        return b11;
    }

    public void setB11(Double b11) {
        this.b11 = b11;
    }

    public Double getB12() {
        return b12;
    }

    public void setB12(Double b12) {
        this.b12 = b12;
    }

    public Double getB13() {
        return b13;
    }

    public void setB13(Double b13) {
        this.b13 = b13;
    }

    public Double getB14() {
        return b14;
    }

    public void setB14(Double b14) {
        this.b14 = b14;
    }

    public Double getB15() {
        return b15;
    }

    public void setB15(Double b15) {
        this.b15 = b15;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getId() {
        return id;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Bolas) {
            Bolas other = (Bolas) obj;
            if (other.b01.equals(this.b01) && other.b02.equals(this.b02)
                    && other.b03.equals(this.b03) && other.b04.equals(this.b04)
                    && other.b05.equals(this.b05) && other.b06.equals(this.b06)
                    && other.b07.equals(this.b07) && other.b08.equals(this.b08)
                    && other.b09.equals(this.b09) && other.b10.equals(this.b10)
                    && other.b11.equals(this.b11) && other.b12.equals(this.b12)
                    && other.b13.equals(this.b13) && other.b14.equals(this.b14)
                    && other.b15.equals(this.b15))
                return true;
            else
                return false;
        } else {
            return super.equals(obj);
        }

    }

    public Integer getSoma() {
        return (int) (this.b01 + this.b02 + this.b03 + this.b04 + this.b05
                + this.b06 + this.b07 + this.b08 + this.b09 + this.b10
                + this.b11 + this.b12 + this.b13 + this.b14 + this.b15);
    }
}
