package io.github.Pavel_Innokentevich_Galanin.gpi_rpodms6_lab4__memory_game;

import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {

    // Объекты картинок (16 штук)
    ImageView iv_0, iv_1, iv_2, iv_3,
            iv_4, iv_5, iv_6, iv_7,
            iv_8, iv_9, iv_10, iv_11,
            iv_12, iv_13, iv_14, iv_15;
    ImageView [] array_images = {
            iv_0, iv_1, iv_2, iv_3,
            iv_4, iv_5, iv_6, iv_7,
            iv_8, iv_9, iv_10, iv_11,
            iv_12, iv_13, iv_14, iv_15
    };

    // открытые картинки
    int first_image_id = -1;
    int second_image_id = -1;

    // Массив картинок (16 штук)
    int [] array_sources_images = {
        R.drawable.apple,
        R.drawable.apple,
        R.drawable.bananas,
        R.drawable.bananas,
        R.drawable.broccoli,
        R.drawable.broccoli,
        R.drawable.durian,
        R.drawable.durian,
        R.drawable.grapes,
        R.drawable.grapes,
        R.drawable.lemon,
        R.drawable.lemon,
        R.drawable.mango,
        R.drawable.mango,
        R.drawable.watermelon,
        R.drawable.watermelon,
    };

    boolean [] array_image_is_opened = {
        false, false, false, false,
        false, false, false, false,
        false, false, false, false,
        false, false, false, false,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Соединяем Java Объекты с картинками по id
        array_images[0] = (ImageView) findViewById(R.id.iv_0);
        array_images[1] = (ImageView) findViewById(R.id.iv_1);
        array_images[2] = (ImageView) findViewById(R.id.iv_2);
        array_images[3] = (ImageView) findViewById(R.id.iv_3);
        array_images[4] = (ImageView) findViewById(R.id.iv_4);
        array_images[5] = (ImageView) findViewById(R.id.iv_5);
        array_images[6] = (ImageView) findViewById(R.id.iv_6);
        array_images[7] = (ImageView) findViewById(R.id.iv_7);
        array_images[8] = (ImageView) findViewById(R.id.iv_8);
        array_images[9] = (ImageView) findViewById(R.id.iv_9);
        array_images[10] = (ImageView) findViewById(R.id.iv_10);
        array_images[11] = (ImageView) findViewById(R.id.iv_11);
        array_images[12] = (ImageView) findViewById(R.id.iv_12);
        array_images[13] = (ImageView) findViewById(R.id.iv_13);
        array_images[14] = (ImageView) findViewById(R.id.iv_14);
        array_images[15] = (ImageView) findViewById(R.id.iv_15);

        for (int i = 0; i < array_images.length; ++i) {
            // Вешаем на картинки слушатель
            array_images[i].setOnClickListener(this);
            // Ставим тэг
            array_images[i].setTag("" + i); // Ставим тэг
        }

        set_images_sources();
        render();
    }

    public void check_win() {
        int count = 0;
        for (int i = 0; i < array_image_is_opened.length; ++i) {
            if (array_image_is_opened[i] == true) {
                count = count + 1;
            }
        }

        if (count == array_image_is_opened.length - 1) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setMessage("Конец игры!");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    public void set_images_sources() {
        for (int i = 0; i < array_images.length; ++i) {
            // ставим картинке изображение
            array_images[i].setImageResource(array_sources_images[i]);
        }
    }

    public void change_image_view(int id) {
        if (first_image_id != -1 && second_image_id != -1 && array_sources_images[first_image_id] == array_sources_images[second_image_id]) {

            //array_images[first_image_id].setOnClickListener(null);
            //array_images[second_image_id].setOnClickListener(null);

            array_image_is_opened[first_image_id] = true;
            array_image_is_opened[second_image_id] = true;

            first_image_id = -1;
            second_image_id = -1;

            return;
        }

        if (first_image_id == id) {
            first_image_id = -1;
            array_image_is_opened[id] = false;
           // first_image_id = second_image_id;
            render();
            return;
        }

        if (second_image_id == id) {
            second_image_id = -1;
            array_image_is_opened[id] = false;
            render();
            return;
        }

        first_image_id = second_image_id;
        second_image_id = id;
        render();
    }

    public void render() {
       // check_two_cards();
        for (int i = 0; i < array_images.length; ++i) {
            if (array_image_is_opened[i] == true) {
                array_images[i].setImageResource(array_sources_images[i]);
                continue;
            }
            if (array_image_is_opened[i] == false) {
                array_images[i].setImageResource(R.drawable.question);
            }
        }

        if (first_image_id != -1) {
            array_images[first_image_id].setImageResource(array_sources_images[first_image_id]);
        }

        if (second_image_id != -1) {
            array_images[second_image_id].setImageResource(array_sources_images[second_image_id]);
        }

        check_win();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_0:
                change_image_view(0);
                break;
            case R.id.iv_1:
                change_image_view(1);
                break;
            case R.id.iv_2:
                change_image_view(2);
                break;
            case R.id.iv_3:
                change_image_view(3);
                break;
            case R.id.iv_4:
                change_image_view(4);
                break;
            case R.id.iv_5:
                change_image_view(5);
                break;
            case R.id.iv_6:
                change_image_view(6);
                break;
            case R.id.iv_7:
                change_image_view(7);
                break;
            case R.id.iv_8:
                change_image_view(8);
                break;
            case R.id.iv_9:
                change_image_view(9);
                break;
            case R.id.iv_10:
                change_image_view(10);
                break;
            case R.id.iv_11:
                change_image_view(11);
                break;
            case R.id.iv_12:
                change_image_view(12);
                break;
            case R.id.iv_13:
                change_image_view(13);
                break;
            case R.id.iv_14:
                change_image_view(14);
                break;
            case R.id.iv_15:
                change_image_view(15);
                break;
        }
    }
}