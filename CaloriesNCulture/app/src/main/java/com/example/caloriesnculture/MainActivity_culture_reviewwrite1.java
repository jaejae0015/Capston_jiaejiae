package com.example.caloriesnculture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.caloriesnculture.R;

public class MainActivity_culture_reviewwrite1 extends AppCompatActivity {
    String sendMsg, receiveMsg;
    EditText txt_culture_review1_title,txt_culture_review1_author,txt_culture_review1_content,review;
    TextView txt_culture_review1_genre;
    String mname, mpeople, mcontent, mgenre,mno,result,receive;
    Button btn_culture_review1_save,btn_culture_review1_genre,btn_culture_review1_cancel;
    public static final String SEND_DATA = "sendData";

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        receive = data.getStringExtra("sendData");//데이터 받아오기
        if(resultCode == RESULT_OK){
            txt_culture_review1_genre = (TextView)findViewById(R.id.txt_culture_review1_genre);
            //String result_genre = data.getExtras().getString(selectDis.RESULT_DATA);
            String result_genre = data.getStringExtra("sendData");//데이터 담는 코드
            txt_culture_review1_genre.setText(result_genre);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_reviewwrite1);

        txt_culture_review1_title = findViewById(R.id.txt_culture_review1_title);//이름
        txt_culture_review1_author= findViewById(R.id.txt_culture_review1_author);//관계자
        //txt_culture_review1_genre = findViewById(R.id.txt_culture_review1_genre);//아직 안만듬
        txt_culture_review1_content = findViewById(R.id.txt_culture_review1_content);//내용
        btn_culture_review1_genre = findViewById(R.id.btn_culture_review1_genre);//장르박기
        btn_culture_review1_save = findViewById(R.id.btn_culture_review1_save);//저장

        btn_culture_review1_genre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//장르 선택 이동
                Intent intent_btn_culture_review1_genre = new Intent(MainActivity_culture_reviewwrite1.this , MainActivity_culture_selectDis1.class);
                startActivityForResult(intent_btn_culture_review1_genre, 0);//액티비티 이동

            }
        });

        //receive = getIntent().getStringExtra(SEND_DATA);//데이터 받아오기
        //if(receive != null){
        //    genre = receive;//장르값 받아오기
        //}
        btn_culture_review1_cancel = findViewById(R.id.btn_culture_review1_cancel);
        btn_culture_review1_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btn_culture_review1_cancel = new Intent(MainActivity_culture_reviewwrite1.this,MainActivity_culture_categorymain1.class);
                startActivity(btn_culture_review1_cancel);
            }
        });


        btn_culture_review1_save = findViewById(R.id.btn_culture_review1_save);
        btn_culture_review1_save.setOnClickListener(new View.OnClickListener() {//저장
            @Override
            public void onClick(View v) {
                try{
                    culture01_01RegisterActivity_insert task = new culture01_01RegisterActivity_insert();
                    if(receive != null){
                        mgenre = receive;
                        //System.out.println("전송완료2");
                        //txtgenre.setText(receive);
                    }
                    String mname = txt_culture_review1_title.getText().toString();//변환
                    String mpeople = txt_culture_review1_author.getText().toString();//변환
                    String mreview = txt_culture_review1_content.getText().toString();//변환
                    //String mno = content.getText().toString();//변환

                    SharedPreferences pref2 = getSharedPreferences("autoFile", MODE_PRIVATE);
                    String id = pref2.getString("id",null);
                    result = task.execute(mno,mname,mpeople,mgenre,mreview,id).get();

                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("DBtest", ".....ERROR.....!");
                }
                Intent intent_btn_culture_review1_save = new Intent(MainActivity_culture_reviewwrite1.this , MainActivity_culture_categorymain1.class);
                startActivity(intent_btn_culture_review1_save);//액티비티 이동

            }
        });

    }
}