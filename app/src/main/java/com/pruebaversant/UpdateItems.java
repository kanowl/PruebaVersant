package com.pruebaversant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    String starttime,finaltime;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Date st;
    public void HoraInicio () throws ParseException {
       st = simpleDateFormat.parse("04/22/2020");
        starttime = simpleDateFormat.format(new Date());
    }


    public void Horafinal () throws ParseException {
        Date ft = simpleDateFormat.parse("04/22/2020");
        long tt = st.getTime()- ft.getTime();
         finaltime = simpleDateFormat.format(new Date());

    }


}
