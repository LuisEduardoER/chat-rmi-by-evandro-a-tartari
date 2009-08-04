package status;

public enum Status {
    OnLine {
        public String getDescricao() {
            return "";
        }
    },
    Ausente {
        public String getDescricao() {
            return "(ausente)";
        }
    },
    Ocupado {
        public String getDescricao() {
            return "(ocupado)";
        }
    };

    public String getDescricao() {
        return "";
    }

}
