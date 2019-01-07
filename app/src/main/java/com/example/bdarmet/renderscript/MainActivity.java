package com.example.bdarmet.renderscript;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.rssample.ScriptC_colorizeRS;
import com.android.rssample.ScriptC_redOnlyRS;
import com.android.rssample.ScriptC_extDynRS;
import com.android.rssample.ScriptC_toGrey;
import com.android.rssample.ScriptC_histogramEqualizationRS;


import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Display zone
    private ImageView imageViewIndex;
    // Picture on the screen
    private Bitmap picture;

    // Save for the picture actually on the screen
    private Bitmap save;
    private BitmapFactory.Options option;
    // Differents button to choose the treatment.
    private Button buttonToHue;
    private Button buttonToGrey;
    private Button buttonToRedOnly;
    private Button buttonDynamiqueExtension;
    private Button buttonDynamiqueExtensionColor;
    private Button buttonHistogramEqualization;
    private Button buttonHistogramEqualizationColor;
    private Button buttonConvolution;
    private Button buttonContour;
    private Button buttonContourSobel;
    private Button buttonRedOnlyRS;
    private Button buttonColorizeRS;
    private Button buttonToGreyRS;
    private Button buttonHistoEqualRS;
    private Button buttonNextPicture;
    private Button buttonReset;
    // Counter to know what picture is on the screen
    int counter;
    int counterSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization of the different variable + display of the first picture
        counter = 0;
        counterSave = 0;
        option = new BitmapFactory.Options();
        option.inMutable = true;
        option.inScaled = false;
        imageViewIndex = findViewById(R.id.picture);
        picture = BitmapFactory.decodeResource(getResources(), R.drawable.contour, option); // Utiliser ces 2 lignes pour changer les images.
        save = picture;
        imageViewIndex.setImageBitmap(picture);

        // Link between the button and the function to do.
        buttonToHue = findViewById(R.id.buttonHue);
        buttonToHue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorize(picture);
            }
        });

        buttonToGrey = findViewById(R.id.buttonGrey);
        buttonToGrey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toGreyDifferent(picture);
            }
        });

        buttonToRedOnly = findViewById(R.id.buttonRedonly);
        buttonToRedOnly.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                redOnly(picture);

            }
        });

        buttonDynamiqueExtension = findViewById(R.id.buttonDynamiqueExtension);
        buttonDynamiqueExtension.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dynamiqueExtension(picture);
            }
        });

        buttonDynamiqueExtensionColor = findViewById(R.id.buttonDynamiqueExtColor);
        buttonDynamiqueExtensionColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dynamiqueExtensionColor(picture);
            }
        });

        buttonHistogramEqualization = findViewById(R.id.buttonHistoEqualization);
        buttonHistogramEqualization.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                histogramEqualization(picture);
            }
        });

        buttonHistogramEqualizationColor = findViewById(R.id.buttonHistoEqualizationColor);
        buttonHistogramEqualizationColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                histogramEqualizationColorV1(picture);
            }
        });


        buttonConvolution = findViewById(R.id.buttonConvolution);
        buttonConvolution.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Convolution convolution = new Convolution(3);
                convolution.setAll(1);
                convolution.convolutionFonction(picture);

            }
        });

        buttonContour= findViewById(R.id.buttonContour);
        buttonContour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Convolution convolution = new Convolution(3);
                convolution.setAll(1);
                convolution.convolutionContour(picture,"Prewitt");

            }
        });

        buttonContourSobel= findViewById(R.id.buttonContourSobel);
        buttonContourSobel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Convolution convolution = new Convolution(3);
                convolution.setAll(1);
                convolution.convolutionContour(picture,"Sobel");

            }
        });

        buttonRedOnlyRS = findViewById(R.id.buttonRedOnlyRS);
        buttonRedOnlyRS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                redOnlyRS(picture);

            }
        });

        buttonToGreyRS= findViewById(R.id.buttonToGreyRS);
        buttonToGreyRS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toGreyRS(picture);
            }
        });

        buttonColorizeRS= findViewById(R.id.buttonColorizeRS);
        buttonColorizeRS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorizeRS(picture);
            }
        });

        buttonHistoEqualRS = findViewById(R.id.buttonEqualHistoRS);
        buttonHistoEqualRS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                histogramEqualizationRS(picture);
            }
        });




        buttonReset= findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (counterSave){
                    case 0:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.contour, option); // Utiliser ces 2 lignes pour changer les images.
                        imageViewIndex.setImageBitmap(picture);
                        break;

                    case 1:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.contrast2, option);
                        imageViewIndex.setImageBitmap(picture);
                        break;

                    case 2:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.contrastecouleur, option);
                        imageViewIndex.setImageBitmap(picture);
                        break;

                    case 3:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.equalizationhistogrey, option);
                        imageViewIndex.setImageBitmap(picture);
                        break;

                    case 4:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.histocouleur, option);
                        imageViewIndex.setImageBitmap(picture);
                        break;

                    case 5:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.extensioncolordyn2, option);
                        imageViewIndex.setImageBitmap(picture);
                        break;


                    case 6:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.couleur, option);
                        imageViewIndex.setImageBitmap(picture);
                        break;

                    case 7:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.couleur2, option);
                        imageViewIndex.setImageBitmap(picture);
                        break;

                    case 8:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.contour2, option);
                        imageViewIndex.setImageBitmap(picture);
                        break;


                }

            }
        });

        buttonNextPicture= findViewById(R.id.buttonNextPicture);
        buttonNextPicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (counter){
                    case 0:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.contour, option); // Utiliser ces 2 lignes pour changer les images.
                        imageViewIndex.setImageBitmap(picture);
                        counter++;
                        counterSave = 0;
                        break;

                    case 1:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.nocontrast, option);
                        imageViewIndex.setImageBitmap(picture);
                        counter++;
                        counterSave++;
                        break;

                    case 2:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.contrastecouleur, option);
                        imageViewIndex.setImageBitmap(picture);
                        counter++;
                        counterSave++;
                        break;

                    case 3:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.equalizationhistogrey, option);
                        imageViewIndex.setImageBitmap(picture);
                        counter++;
                        counterSave++;
                        break;

                    case 4:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.histocouleur, option);
                        imageViewIndex.setImageBitmap(picture);
                        counter++;
                        counterSave++;
                        break;


                    case 5:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.extensioncolordyn2, option);
                        imageViewIndex.setImageBitmap(picture);
                        counter++;
                        counterSave++;
                        break;

                    case 6:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.couleur, option);
                        imageViewIndex.setImageBitmap(picture);
                        counter++;
                        counterSave++;
                        break;

                    case 7:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.couleur2, option);
                        imageViewIndex.setImageBitmap(picture);
                        counter++;
                        counterSave++;
                        break;

                    case 8:
                        picture = BitmapFactory.decodeResource(getResources(), R.drawable.contour2, option);
                        imageViewIndex.setImageBitmap(picture);
                        counter = 0;
                        counterSave++;
                        break;


                }

            }
        });




    }

    /**
     * Transform a color picture to a grey picture
     * @param bmp Picture to be changed
     */
    public void toGrey(Bitmap bmp) {
        int R, G, B;
        int colorPixel;
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                colorPixel = bmp.getPixel(x, y); // We get the value of a pixel at the position x,y
                R = Color.red(colorPixel); // We get the differents value of RGB space
                G = Color.green(colorPixel);
                B = Color.blue(colorPixel);
                int colorNewPixel = (int) (0.3 * R + 0.59 * G + 0.11 * B); // We calcul the grey
                bmp.setPixel(x, y, Color.rgb(colorNewPixel, colorNewPixel, colorNewPixel)); // We transform the pixel with a grey color

            }
        }


    }
    /**
     * Transform a color picture to a grey picture. Same fonction as toGrey but faster.
     * @param bmp Picture to  be changed
     */
    public void toGreyDifferent(Bitmap bmp) {
        int R, G, B, colorNewPixel;
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int tab[] = new int[width * height];
        bmp.getPixels(tab, 0, width, 0, 0, width, height);
        for (int i = 0; i < tab.length; i++) {
            R = Color.red(tab[i]);
            G = Color.green(tab[i]);
            B = Color.blue(tab[i]);
            colorNewPixel = (int) (0.3 * R + 0.59 * G + 0.11 * B);
            tab[i] = Color.rgb(colorNewPixel, colorNewPixel, colorNewPixel);
        }
        bmp.setPixels(tab, 0, width, 0, 0, width, height);
    }

    /**
     * change at random the hue of a picture
     * @param bmp Picture to be changed
     */
    public void colorize(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int tab_pixel[] = new int[width * height];
        float tab_HSV[] = new float[3];
        Random aleatoire = new Random();
        int mystere = aleatoire.nextInt(360);


        bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);
        for (int i = 0; i < tab_pixel.length; i++) {
            Color.RGBToHSV(Color.red(tab_pixel[i]), Color.green(tab_pixel[i]), Color.blue(tab_pixel[i]), tab_HSV);
            tab_HSV[0] = mystere;
            tab_pixel[i] = Color.HSVToColor(tab_HSV);
        }
        bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);
    }

    /**
     * Keep only the color red on the picture
     * @param bmp Picture to be  changed
     */
    public void redOnly(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int tab_pixel[] = new int[width * height];
        float tab_HSV[] = new float[3];

        bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);
        for (int i = 0; i < tab_pixel.length; i++) {
            Color.RGBToHSV(Color.red(tab_pixel[i]), Color.green(tab_pixel[i]), Color.blue(tab_pixel[i]), tab_HSV);
            if (tab_HSV[0] >= 15 && tab_HSV[0] <= 345) {
                int R = Color.red(tab_pixel[i]);
                int G = Color.green(tab_pixel[i]);
                int B = Color.blue(tab_pixel[i]);
                int colorNewPixel = (int) (0.3 * R + 0.59 * G + 0.11 * B);
                tab_pixel[i] = Color.rgb(colorNewPixel, colorNewPixel, colorNewPixel);
            }

        }
        bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);
    }

    /* Question 1.1 TD 3 */
    /* Marche Nickel */

    /**
     * Increase the contrast of the picture
     * @param bmp Picture to be changed
     */
    public void dynamiqueExtension(Bitmap bmp){
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        int[] pixels = new int[w*h];
        int[] LUT = new int[256];
        bmp.getPixels(pixels,0,w,0,0,w,h);
        int min = 255;
        int max = 0;
        int grey;

        for(int i = 1; i < w*h; ++i){
            grey = Color.red(pixels[i]);
            if(min > grey){
                min = grey;
            }
            if(max < grey){
                max = grey;
            }
        }

        //Initialization of the lut
        for(int i = 0; i< 256 ; i++){
            LUT[i]=(255*(i-min))/(max-min);
        }

        //Transformation calcul
        for(int i = 0; i < w*h; ++i){
            grey = Color.red(pixels[i]);
            pixels[i] = Color.rgb(LUT[grey],LUT[grey],LUT[grey]);
        }

        bmp.setPixels(pixels,0,w,0,0,w,h);
    }

    /* Question 1.2 TD 3 */
    /* Marche pas trop trop  ET PAS TROP IMPORTANT*/
    public void dynamiqueExtensionV2(Bitmap bmp){
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        int[] tab_pixels = new int[w*h];
        bmp.getPixels(tab_pixels,0,w,0,0,w,h);
        int min = 0;
        int max = 255;
        int[] histogram = new int[256];
        int color;

        //mise en place de l'histogramme
        for(int i = 0; i < w*h; ++i){
            color = Color.red(tab_pixels[i]);
            histogram[color]++;

        }

        int pourcentagePixelmin= (1*w*h)/100;
        int pourcentagePixelmax= (w*h)-((1*w*h)/100);
        int compteur = 0;
        for(int i = 0;i < 256;i++){
            compteur = compteur + histogram[i];
            if(compteur == pourcentagePixelmin){
                min = i;
                System.out.println(i);
            }
            if(compteur == pourcentagePixelmax){
                max = i;
                System.out.println("LE I EST ICI !!!!!" + "" + i);
            }

        }

        for(int i = 0; i < w*h; ++i){
            color = Color.red(tab_pixels[i]);
            color = (255 / (max - min)) * (color - min);
            tab_pixels[i] = Color.rgb(color,color,color);
        }

        bmp.setPixels(tab_pixels,0,w,0,0,w,h);
    }

    /* Question 1.3 TD 3 */
    /* Tester avec la luminosité */

    /**
     * Increase the contrast of the picture.
     * @param bmp Picture to be changed
     */
    public void dynamiqueExtensionColor(Bitmap bmp){
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        int[] pixels = new int[w*h];
        bmp.getPixels(pixels,0,w,0,0,w,h);
        int[] LUT_Red= new int[256]; // Initialize the differents LUT.
        int[] LUT_Green= new int[256];
        int[] LUT_Blue = new int[256];

        int minRed = 255;
        int maxRed = 0;
        int minGreen = 255;
        int maxGreen = 0;
        int minBlue = 255;
        int maxBlue  = 0;

        int colorRed;
        int colorBlue;
        int colorGreen;

        for(int i = 0; i < pixels.length; i++){ // We determine the max and min for each value of RGB space
            colorRed = Color.red(pixels[i]);
            if(minRed > colorRed){
                minRed = colorRed;
            }
            if(maxRed < colorRed){
                maxRed = colorRed;
            }

            colorGreen = Color.green(pixels[i]);
            if(minGreen > colorGreen){
                minGreen = colorGreen;
            }
            if(maxGreen < colorGreen){
                maxGreen  = colorGreen;
            }

            colorBlue = Color.blue(pixels[i]);
            if(minBlue > colorBlue){
                minBlue = colorBlue;
            }
            if(maxBlue < colorBlue){
                maxBlue = colorBlue;
            }

        }

        for(int i = 0; i < 256; i++){
            LUT_Red[i] = (255 *(i - minRed))/(maxRed-minRed);
            LUT_Blue[i] = (255 *(i - minBlue))/(maxBlue-minBlue);
            LUT_Green[i] =  (255 *(i - minGreen))/(maxGreen-minGreen);
        }


        for(int i = 0; i < pixels.length; i++){ // Set the new value of each pixel of the picture
            int red = Color.red(pixels[i]);
            int green = Color.green(pixels[i]);
            int blue = Color.blue(pixels[i]);
            int new_red = LUT_Red[red];
            int new_green =  LUT_Green[green];
            int new_blue = LUT_Blue[blue];
            pixels[i] = Color.rgb(new_red,new_green,new_blue);
        }

        bmp.setPixels(pixels,0,w,0,0,w,h);
    }

    /* Question 2.1 TD 3 */

    /**
     * Increase constrat by histogram equalization for a grey picture
     * @param bmp Picture to be changed
     */
    public void histogramEqualization(Bitmap bmp){
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] histogram = new int[256];
        int[] tab_pixel = new int[width * height];
        bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);


        for(int i = 0; i < tab_pixel.length ; i++){
            histogram[Color.red(tab_pixel[i])]++; //histogram Calcul
        }
        for(int i = 1 ; i < 256 ; i++){
            histogram[i]=histogram[i]+histogram[i-1]; // Cumulate Histogram calcul
        }


        for(int i = 0 ; i < tab_pixel.length ; i++){
            int cumulateHistoValue = histogram[Color.red(tab_pixel[i])]; // The value of the cumulative histogram is recovered as a function of the grey value of the pixel
            int grey = (cumulateHistoValue*255)/(width*height); // The new grey value is determined.
            tab_pixel[i]= Color.rgb(grey,grey,grey); // then affected

        }
        bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);
    }

    /* Question 2.2 TD 3*/

    /**
     * Increase constrat by histogram equalization for a color picture
     * @param bmp Picture to be changed
     */
    public void histogramEqualizationColorV1(Bitmap bmp){

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] histogram = new int[256];
        int[] tab_pixel = new int[width * height];
        bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);

        //Histogram calcul
        for(int i = 0; i < tab_pixel.length ; i++){
            int indice = (Color.red(tab_pixel[i])+Color.blue(tab_pixel[i])+Color.green(tab_pixel[i]))/3;
            histogram[indice]++;
        }

        for(int i = 1 ; i < 256 ; i++){
            histogram[i] = histogram[i] + histogram[i-1];
        }

        for(int i = 0 ; i < tab_pixel.length ; i++){
            int red = (histogram[Color.red(tab_pixel[i])]*255)/(width*height);
            int green = (histogram[Color.green(tab_pixel[i])]*255)/(width*height);
            int blue = (histogram[Color.blue(tab_pixel[i])]*255)/(width*height);
            tab_pixel[i] = Color.rgb(red,green,blue);
        }
        bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);

    }

    /* Question 2.2 TD3 */
    /* 2eme manière */


    /**
     * Increase constrat by histogram equalization for a color picture. Second method.
     * @param bmp Picture to be changed
     */
    public void histogramEqualizationColorV2(Bitmap bmp){ /* RGB Indépendant */

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] histogram_red = new int[256];
        int[] histogram_green = new int[256];
        int[] histogram_blue = new int[256];
        int[] tab_pixel = new int[width * height];
        bmp.getPixels(tab_pixel, 0, width, 0, 0, width, height);

        //histogram calcul for each value of RGB Space : red, green blue.
        for(int i = 0; i < tab_pixel.length ; i++){
            histogram_red[Color.red(tab_pixel[i])]++;
            histogram_green[Color.green(tab_pixel[i])]++;
            histogram_blue[Color.blue(tab_pixel[i])]++;
        }

        //histogram cumulate for each value of rgb space : red, green and blue
        for(int i = 1 ; i < 256 ; i++){
            histogram_red[i]=histogram_red[i]+histogram_red[i-1];
            histogram_green[i]=histogram_green[i]+histogram_green[i-1];
            histogram_blue[i]=histogram_blue[i]+histogram_blue[i-1];
        }

        // Affectation of new value for each value of rgb space for a pixel of the picture
        for(int i = 0 ; i < tab_pixel.length ; i++){
            int cumulateHistoValueRed = histogram_red[Color.red(tab_pixel[i])];
            int red = (cumulateHistoValueRed*255)/(width*height);
            int cumulateHistoValueBlue = histogram_blue[Color.blue(tab_pixel[i])];
            int blue = (cumulateHistoValueBlue*255)/(width*height);
            int cumulateHistoValueGreen = histogram_green[Color.green(tab_pixel[i])];
            int green = (cumulateHistoValueGreen*255)/(width*height);
            tab_pixel[i]= Color.rgb(red,green,blue);

        }
        bmp.setPixels(tab_pixel, 0, width, 0, 0, width, height);
    }

    /* Question 1 + 2 TD 4 */

    /**
     * Convolution class to use different method bind to the utilisation of different filter.
     * @author Benjamin DARMET
     */
    public class Convolution {

        public int SIZE = 3;
        public int[][] Matrix;
        public int Factor = SIZE*SIZE;
        private int [][] prewittX;
        private int [][] prewittY;
        private int[][] sobelX;
        private int[][] sobelY;
        public Convolution(int size){
            this.SIZE = size; // Size of a matrix
            Matrix = new int[size][size];
            prewittX = new int [3][3];
            prewittY = new int[3][3];

            //Prewit initialisation
            for(int x = 0; x < 3 ; x++){
                for(int y = 0; y < 3; y++){
                    if(x== 0){
                        prewittX[x][y] = -1;
                    }else if(x == 1){
                        prewittX[x][y] = 0;
                    }else{
                        prewittX[x][y] = 1;
                    }
                }
            }

            for(int x = 0; x < 3 ; x++){
                for(int y = 0; y < 3; y++){
                    if(y== 0){
                        prewittY[x][y] = -1;
                    }else if(y == 1){
                        prewittY[x][y] = 0;
                    }else{
                        prewittY[x][y] = 1;
                    }
                }
            }

            // Sobel initialisation
            sobelX = new int[3][3];
            sobelX[0][0] = -1;
            sobelX[0][1] = -2;
            sobelX[0][2] = -1;
            sobelX[1][0] = 0;
            sobelX[1][1] = 0;
            sobelX[1][2] = 0;
            sobelX[2][0] = 1;
            sobelX[2][1] = 2;
            sobelX[2][2] = 1;

            sobelY = new int[3][3];
            sobelY[0][0] = -1;
            sobelY[1][0] = -2;
            sobelY[2][0] = -1;
            sobelY[0][1] = 0;
            sobelY[1][1] = 0;
            sobelY[2][1] = 0;
            sobelY[0][2] = 1;
            sobelY[1][2] = 2;
            sobelY[2][2] = 1;
        }

        /**
         * Use for a matrice where the value are the same.
         * @param value value of each case in the matrice
         */
        public void setAll(int  value){
            for(int x = 0 ; x < SIZE; x++){
                for(int y = 0 ; y < SIZE; y++){
                    Matrix[x][y] = value;
                }
            }

        }

        /**
         * Blurs an image. Need to be applied many time
         * @param bmp Image to blur
         */
         public void convolutionFonction(Bitmap bmp){
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] tabPixels = new int [width*height];
            int[] tabPixelsSave = new int [width*height];

            bmp.getPixels(tabPixels, 0, width, 0, 0, width, height);
            bmp.getPixels(tabPixelsSave, 0,width,0,0,width,height);


            for(int x = 0; x < width-SIZE; x++){ // we go through the basic image
                for(int y = 0; y < height-SIZE ; y++){

                    int position = ((x+ (SIZE/2)) + ((y+(SIZE/2))*width)); // Position at the center of the matrice. Always start to 1.1 on the picture.
                    int sumRed = 0;
                    int sumGreen = 0;
                    int sumBlue = 0;

                    for(int matriceX = 0; matriceX < SIZE ; matriceX++){ // We get the value of each pixel inside the matrice.
                        for(int matriceY = 0; matriceY < SIZE ; matriceY++){

                            int pixel = tabPixelsSave[x+matriceX +(y+matriceY)*width];

                            int mValue = Matrix[matriceX][matriceY];

                            sumRed = sumRed + (Color.red(pixel)*(mValue));
                            sumGreen = sumGreen + (Color.green(pixel)*(mValue));
                            sumBlue = sumBlue + (Color.blue(pixel)*(mValue));

                        }
                    }
                     // Average filer applyed.
                    // Normalization
                    int newRed = (sumRed / this.Factor);
                    int newGreen = (sumGreen / this.Factor);
                    int newBlue = (sumBlue / this.Factor);

                tabPixels[position] = Color.rgb(newRed,newGreen,newBlue);
                }
            }

            bmp.setPixels(tabPixels ,0, width, 0, 0, width, height);

        }


        /**
         *  Highlight the contours of each element of the image
         * @param bmp Picture to be changed
         * @param type the filter to use : Prewitt or Sobel.
         */
        public void convolutionContour(Bitmap bmp, String type){
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            int[] tabPixels = new int [width*height];
            int[] tabPixelsSave = new int [width*height];

            bmp.getPixels(tabPixels, 0, width, 0, 0, width, height);
            bmp.getPixels(tabPixelsSave, 0,width,0,0,width,height);


            for(int x = 0; x < width-SIZE; x++){ // we go through the basic image
                for(int y = 0; y < height-SIZE ; y++){

                    int position = ((x+ (SIZE/2)) + ((y+(SIZE/2))*width)); // Position at the center of the matrice. Always start to 1.1 on the picture.



                    int gX = 0;
                    int gY = 0;
                    for(int matriceX = 0; matriceX < SIZE ; matriceX++) {// We get the value of each pixel inside the matrice.
                        for (int matriceY = 0; matriceY < SIZE; matriceY++) {

                            int pixel = tabPixelsSave[x + matriceX + (y + matriceY) * width];
                            if(type == "Prewitt"){                                                  // We choose the filter to apply.
                                gX = gX +(Color.red(pixel)*prewittX[matriceX][matriceY]);
                                gY = gY +(Color.red(pixel)*prewittY[matriceX][matriceY]);
                            }else{
                                gX = gX +(Color.red(pixel)*sobelX[matriceX][matriceY]);
                                gY = gY +(Color.red(pixel)*sobelY[matriceX][matriceY]);
                            }


                        }
                    }
                    if(type == "Prewitt"){ // Normalization
                        gX = gX/3;
                        gY = gY/3;
                    }else{
                        gX = gX/4;
                        gY = gY/4;
                    }

                    int newG = (int)Math.sqrt(Math.pow(gX,2.0) + Math.pow(gY, 2.0));
                    if(newG > 255){
                        newG = 255;
                    }


                    tabPixels[position] = Color.rgb(newG,newG,newG);

                }
            }
            bmp.setPixels(tabPixels ,0, width, 0, 0, width, height);

        }

    }


    /* PARTIE RENDER SCRIPT  ! */

    private void toGreyRS(Bitmap bmp) {
        //1)  Creer un  contexte  RenderScript
        RenderScript rs = RenderScript.create(this);
        //2)  Creer  des  Allocations  pour  passer  les  donnees
        Allocation input = Allocation.createFromBitmap(rs, bmp);
        Allocation output = Allocation.createTyped(rs, input.getType());
        //3)  Creer le  script
        ScriptC_toGrey greyScript = new ScriptC_toGrey(rs);
        //6)  Lancer  le noyau
        greyScript.forEach_toGrey(input, output);
        //7)  Recuperer  les  donnees  des  Allocation(s)
        output.copyTo(bmp);
        //8)  Detruire  le context , les  Allocation(s) et le  script
        input.destroy();
        output.destroy();
        greyScript.destroy();
        rs.destroy();
    }

    private void colorizeRS(Bitmap bmp) {
        Random aleatoire = new Random();
        float mystere = (float) (aleatoire.nextInt(360));
        //1)  Creer un  contexte  RenderScript
        RenderScript rs = RenderScript.create(this);
        //2)  Creer  des  Allocations  pour  passer  les  donnees
        Allocation input = Allocation.createFromBitmap(rs, bmp);
        Allocation output = Allocation.createTyped(rs, input.getType());
        //3)  Creer le  script
        ScriptC_colorizeRS colorizeScript = new ScriptC_colorizeRS(rs);
        //5)  Initialiser  les  variables  globales  potentielles
        colorizeScript.set_t(mystere);
        //6)  Lancer  le noyau
        colorizeScript.forEach_colorizeRS(input, output);
        //7)  Recuperer  les  donnees  des  Allocation(s)
        output.copyTo(bmp);
        //8)  Detruire  le context , les  Allocation(s) et le  script
        input.destroy();
        output.destroy();
        colorizeScript.destroy();
        rs.destroy();
    }

    private void redOnlyRS(Bitmap bmp){
        //1)  Creer un  contexte  RenderScript
        RenderScript rs = RenderScript.create(this);
        //2)  Creer  des  Allocations  pour  passer  les  donnees
        Allocation input = Allocation.createFromBitmap(rs, bmp);
        Allocation output = Allocation.createTyped(rs, input.getType());
        //3)  Creer le  script
        ScriptC_redOnlyRS redOnlyScript = new ScriptC_redOnlyRS(rs);
        //6)  Lancer  le noyau
        redOnlyScript.forEach_redOnlyRS(input, output);
        //7)  Recuperer  les  donnees  des  Allocation(s)
        output.copyTo(bmp);
        //8)  Detruire  le context , les  Allocation(s) et le  script
        input.destroy();
        output.destroy();
        redOnlyScript.destroy();
        rs.destroy();
    }

    private void histogramEqualizationRS(Bitmap bmp){
        //Get image size
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        //Create new bitmap
        Bitmap res = bmp.copy(bmp.getConfig(), true);

        //Create renderscript
        RenderScript rs = RenderScript.create(this);

        //Create allocation from Bitmap
        Allocation allocationA = Allocation.createFromBitmap(rs, res);

        //Create allocation with same type
        Allocation allocationB = Allocation.createTyped(rs, allocationA.getType());

        //Create script from rs file.
        ScriptC_histogramEqualizationRS histEqScript = new ScriptC_histogramEqualizationRS(rs);

        //Set size in script
        histEqScript.set_size(width*height);

        //Call the first kernel. It creates the histogram.
        histEqScript.forEach_root(allocationA, allocationB);

        //Call the rs method to compute the remap array. It creates the cumulate histogram.
        histEqScript.invoke_createRemapArray();

        //Call the second kernel. It remap from YUV to RGB.
        histEqScript.forEach_remaptoRGB(allocationB, allocationA);

        //Copy script result into bitmap
        allocationA.copyTo(bmp);

        //Destroy everything to free memory
        allocationA.destroy();
        allocationB.destroy();
        histEqScript.destroy();
        rs.destroy();

    }

    private void dynExtensionRS(Bitmap bmp){

        //Create new bitmap
        Bitmap res = bmp.copy(bmp.getConfig(), true);

        //Create renderscript
        RenderScript rs = RenderScript.create(this);

        //Create allocation from Bitmap
        Allocation allocationA = Allocation.createFromBitmap(rs, res);

        //Create allocation with same type
        Allocation allocationB = Allocation.createTyped(rs, allocationA.getType());

        //Create script from rs file.
        ScriptC_extDynRS test= new ScriptC_extDynRS(rs);

        test.invoke_initMinAndMax();
        //Call the first kernel. It creates the min and max
        test.forEach_minAndMax(allocationA, allocationB);

        //Call the rs method to compute the remap array. It creates the LUT
        test.invoke_initLUT();
        test.invoke_changeLUT();

        test.forEach_transformation(allocationB, allocationA);
        //Copy script result into bitmap
        allocationA.copyTo(bmp);

        //Destroy everything to free memory
        allocationA.destroy();
        allocationB.destroy();
        test.destroy();
        rs.destroy();

    }



}