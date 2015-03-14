package com.example.kdh.mycamera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private Preview mPreview;
    ArrayList<com.example.kdh.mycamera.Color> arr;
    ListView listView;
    TextView textView;
    TextView text_R;
    TextView text_G;
    TextView text_B;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//화면 안꺼지게

        // Hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,

        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Create our Preview view and set it as the content of our activity.

       /* mPreview = new Preview(this);
        setContentView(mPreview);*/
        setContentView(R.layout.activity_main);


        textView=(TextView)findViewById(R.id.pixel);
        text_R=(TextView)findViewById(R.id.R);
        text_G=(TextView)findViewById(R.id.G);
        text_B=(TextView)findViewById(R.id.B);
        mPreview = (Preview)findViewById(R.id.p);

        mPreview.setText(textView,text_R,text_G,text_B);



        //Json

        HandleJSON json = new HandleJSON("http://www.ibtk.kr/ehcdCbEColor/54db0d593c28fca2e7a6262d0a26f3ef?model_query_pageable={%27enable%27:false}");
        arr=json.fetchJSON();
        listView=(ListView)findViewById(R.id.listview);
        Myadaptor myadaptor=new Myadaptor(MainActivity.this,R.layout.item,arr);
        listView.setAdapter(myadaptor);
    }
    public class ViewHolder {
        TextView tex1;
        TextView tex2;
        TextView tex3;
        LinearLayout rgbLayout;
        ImageView color[];
    }

    class Myadaptor extends ArrayAdapter<com.example.kdh.mycamera.Color> {
        ArrayList<com.example.kdh.mycamera.Color> arr1;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
        ViewHolder holder;

        public Myadaptor(Context context, int resource, ArrayList<com.example.kdh.mycamera.Color> objects) {
            super(context, resource, objects);
            arr1=objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            holder = new ViewHolder();
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.item, null);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }

            holder.tex1= (TextView) v.findViewById(R.id.Seq);
            holder.tex2= (TextView) v.findViewById(R.id.AlikeWord);
            holder.tex3= (TextView) v.findViewById(R.id.Typ);

            holder.tex1.setText(arr1.get(position).cbaSeq+"");
            holder.tex2.setText(arr1.get(position).cbaAlikeWord);
            holder.tex3.setText(arr1.get(position).cbaTyp+"");
            holder.rgbLayout = (LinearLayout) v.findViewById(R.id.rgbColorList);
            holder.rgbLayout.removeAllViews();

            holder.color = new ImageView[arr.get(position).cbaTyp];

            for(int i=0; i<holder.color.length; i++) {
                holder.color[i] = new ImageView(MainActivity.this);
                holder.color[i].setLayoutParams(params);
            }
            if(holder.color.length == 1) {
                holder.color[0].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb1.getR(), arr.get(position).rgb1.getG(), arr.get(position).rgb1.getB()));
                holder.rgbLayout.addView(holder.color[0]);
            }
            else if (holder.color.length == 2) {
                holder.color[0].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb1.getR(),arr.get(position).rgb1.getG(),arr.get(position).rgb1.getB()));
                holder.color[1].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb2.getR(),arr.get(position).rgb2.getG(),arr.get(position).rgb2.getB()));

                holder.rgbLayout.addView(holder.color[0]);
                holder.rgbLayout.addView(holder.color[1]);
            } else if (holder.color.length == 3) {
                holder.color[0].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb1.getR(),arr.get(position).rgb1.getG(),arr.get(position).rgb1.getB()));
                holder.color[1].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb2.getR(),arr.get(position).rgb2.getG(),arr.get(position).rgb2.getB()));
                holder.color[2].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb3.getR(),arr.get(position).rgb3.getG(),arr.get(position).rgb3.getB()));

                holder.rgbLayout.addView(holder.color[0]);
                holder.rgbLayout.addView(holder.color[1]);
                holder.rgbLayout.addView(holder.color[2]);
            } else if(holder.color.length == 4) {
                holder.color[0].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb1.getR(), arr.get(position).rgb1.getG(), arr.get(position).rgb1.getB()));
                holder.color[1].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb2.getR(), arr.get(position).rgb2.getG(), arr.get(position).rgb2.getB()));
                holder.color[2].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb3.getR(), arr.get(position).rgb3.getG(), arr.get(position).rgb3.getB()));
                holder.color[3].setBackgroundColor(android.graphics.Color.rgb(arr.get(position).rgb4.getR(), arr.get(position).rgb4.getG(), arr.get(position).rgb4.getB()));

                holder.rgbLayout.addView(holder.color[0]);
                holder.rgbLayout.addView(holder.color[1]);
                holder.rgbLayout.addView(holder.color[2]);
                holder.rgbLayout.addView(holder.color[3]);
            }

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, holder.rgbLayout.getChildCount() + "", Toast.LENGTH_SHORT).show();
                }
            });
            return v;
        }
    }
}
class Preview extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {
    SurfaceHolder mHolder;
    TextView text;
    TextView text_R;
    TextView text_G;
    TextView text_B;


    Camera mCamera;

    //This variable is responsible for getting and setting the camera settings
    private Camera.Parameters parameters;
    //this variable stores the camera preview size
    private Camera.Size previewSize;
    //this array stores the pixels as hexadecimal pairs
    private int[] pixels;

    public void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }



    public void setText(TextView tv,TextView R,TextView G,TextView B) {
        text = tv;
        text_R=R;
        text_G=G;
        text_B=B;

        text.setText("TextCHECKED");
    }


    Preview(Context context) {
        super(context);

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        init();
    }

    public Preview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Preview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, acquire the camera and tell it where
        // to draw.
        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(holder);

            //sets the camera callback to be the one defined in this class
            mCamera.setPreviewCallback(this);

            ///initialize the variables
            parameters = mCamera.getParameters();
            previewSize = parameters.getPreviewSize();
            pixels = new int[previewSize.width * previewSize.height];

        } catch (IOException exception) {
            mCamera.release();
            mCamera = null;
            // TODO: add more exception handling logic here
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface will be destroyed when we return, so stop the preview.
        // Because the CameraDevice object is not a shared resource, it's very
        // important to release it when the activity is paused.
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        parameters.setPreviewSize(w, h);
        //set the camera's settings
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        //transforms NV21 pixel data into RGB pixels
        decodeYUV420SP(pixels, data, previewSize.width,  previewSize.height);
        text.setText("픽셀정보"+Integer.toHexString(pixels[(1280*(previewSize.height/2)+(1280/2))]));//(previewSize.height*(previewSize.width/2))+previewSize.height/2
        text_R.setText("R="+Integer.toHexString(pixels[(1280*(previewSize.height/2)+(1280/2))]-pixels[(1280*(previewSize.height/2)+(1280/2))]%65536));
        text_G.setText("G="+Integer.toHexString(pixels[(1280*(previewSize.height/2)+(1280/2))]%65536-pixels[(1280*(previewSize.height/2)+(1280/2))]%256));
        text_B.setText("B="+Integer.toHexString(pixels[(1280*(previewSize.height/2)+(1280/2))]%256));

        String str = text_R.getText().toString();
        str = str.substring(4,6);
        text_R.setText("R="+str);
        String str1 = text_G.getText().toString();
        str1 = str1.substring(6,8);
        text_G.setText("G="+str1);
        String str2 = text_B.getText().toString();
        str2 = str2.substring(8,10);
        text_B.setText("B="+str2);
        // 커밋을 위해 테스트용 주석처리
        //Outuput the value of the top left pixel in the preview to LogCat
        Log.i("Pixels", "The top right pixel has the following RGB (hexadecimal) values:"
                + Integer.toHexString(pixels[0]));
    }

    //Method from Ketai project! Not mine! See below...
    void decodeYUV420SP(int[] rgb, byte[] yuv420sp, int width, int height) {

        final int frameSize = width * height;
        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0)
                    y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }



                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0)                  r = 0;               else if (r > 262143)
                    r = 262143;
                if (g < 0)                  g = 0;               else if (g > 262143)
                    g = 262143;
                if (b < 0)                  b = 0;               else if (b > 262143)
                    b = 262143;

                //rgb[yp] = 000000 | ((r << 4) & 0000) | ((g) & 00) | ((b >> 8) & 0);
                rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
            }
        }

    }
}
/*class Preview extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder mHolder;
    Camera mCamera;

    Preview(Context context) {
        super(context);
        // SurfaceHolder.Callback을 설정함으로써 Surface가 생성/소멸되었음을
        // 알 수 있습니다.

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // Surface가 생성되었다면, 카메라의 인스턴스를 받아온 후 카메라의

        // Preview 를 표시할 위치를 설정합니다.

        mCamera = Camera.open();
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException exception) {
            mCamera.release();
            mCamera = null;
            // TODO: add more exception handling logic here
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // 다른 화면으로 돌아가면, Surface가 소멸됩니다. 따라서 카메라의 Preview도

        // 중지해야 합니다. 카메라는 공유할 수 있는 자원이 아니기에, 사용하지 않을

        // 경우 -액티비티가 일시정지 상태가 된 경우 등 - 자원을 반환해야합니다.

        mCamera.stopPreview();
        mCamera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // 표시할 영역의 크기를 알았으므로 해당 크기로 Preview를 시작합니다.
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewSize(w,h );
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }

}*/