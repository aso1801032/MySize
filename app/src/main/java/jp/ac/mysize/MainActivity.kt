package jp.ac.mysize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //push練習コメント

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heightButton.setOnClickListener {
            val intent = Intent(this,HeightActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //共有プレファレンスのインスタンスから保存済みの値を取り出す
        val editNeck = pref.getString("Neck", " ")
        val editSleeve = pref.getString("Sleeve", " ")
        val editWaist = pref.getString("Waist", " ")
        val editInseam = pref.getString("Inseam", " ")
        //保存値で表示を上書き
        neck.setText(editNeck)
        sleeve.setText(editSleeve)
        waist.setText(editWaist)
        inseam.setText(editInseam)

        save.setOnClickListener { onSaveTapped() }

        //身長アイコンボタンクリック時の画面遷移設定
        heightButton.setOnClickListener{
            var intent = Intent(this,HeightActivity::class.java);
            //画面遷移メソッドを実行
            this.startActivity(intent);
        }
    }

    //保存ボタンが押されたらコールバックする処理
    private fun onSaveTapped(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            this.putString("NECK", neck.text.toString())
            this.putString("SLEEVE", sleeve.text.toString())
            this.putString("WAIST", waist.text.toString())
            this.putString("INSEAM", inseam.text.toString())
        }
    }
}
