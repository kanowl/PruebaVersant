package com.pruebaversant;

public class UpdateItems {

    public String Cefr ( String gse1) {
        String cefr ="A2";
        int auxgse = Integer.parseInt(gse1);
        if (auxgse>42)
            cefr="B1";
        if (auxgse>57)
            cefr="B2";
        if (auxgse>75)
            cefr="C1";

        return cefr;

    }


}
