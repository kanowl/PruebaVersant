package com.pruebaversant;


import android.util.Log;




public class UpdateItems implements Runnable{






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

    public int Randompista (int numero, int auxnum){

        if(numero == 0){
            numero =(int) (Math.random() * 5) + 1;
            if (auxnum !=0){
                while (auxnum == numero){
                    numero =(int) (Math.random() * 5) ;
                }
            }
        }

        return numero;
    }

    Thread hilo;
    boolean iniciado = true;

    public void HoraInicio(int bandera){
        Log.d("Bandera...", String.valueOf(bandera));

        if (bandera == 0) {
            hilo = new Thread(this);

            pausar = false;
            hilo.start();
            iniciado = false;
        } else {
        }


    }

    public long HoraFinal(long tiempo){
        pausar = true;
        iniciado = true;
        long result = minutos + tiempo;
        minutos =0;
        Log.d("tiempo ...", String.valueOf(result));

        return result;

    }



    static boolean pausar;
   static long minutos;

    public void run() {

        Integer  segundos = 0, milesimas = 0;
        //min es minutos, seg es segundos y mil es milesimas de segundo

        try {


            while (!pausar) {
                // Ajuste al codigo: se elimina while y se anexa un if donde se niega la variable pausar

                if (!pausar) {
                    //**************************************************************************************

                    Thread.sleep(4);
                    //Incrementamos 4 milesimas de segundo
                    milesimas += 4;
                    //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                    //y las milesimas de segundo de nuevo a 0
                    if (milesimas == 1000) {
                        milesimas = 0;
                        segundos += 1;
                        Log.d("Tiempo", "segundos "+ segundos);
                        //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                        //y los segundos vuelven a 0
                        if (segundos == 60) {
                            segundos = 0;

                            minutos++;
                            Log.d("Tiempo", "minutos: "+ minutos);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Log.d("Tiempo", "error al correr el método run");
        }


    }







}
