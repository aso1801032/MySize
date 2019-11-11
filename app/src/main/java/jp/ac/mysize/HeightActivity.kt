package jp.ac.mysize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.SeekBar
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_height.*

class HeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)
    }

    override fun onResume() {
        super.onResume()
        //スピナーにitem選択肢が選ばれた時のコールバックメソッド
        spinner.onItemSelectedListener =
            object:AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //選択肢を取得するためにスピナーのインスタンスを取得
                    val spinner = parent as? Spinner
                    //選択肢を取得
                    val item = spinner?.selectedItem as? String
                    //取得した値を身長の値のテキストビューに上書き
                    item?.let {
                        if (it.isNotEmpty()){ height.text = it }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        //シークバーの処理
        var pref = PreferenceManager.getDefaultSharedPreferences(this);
        val heightVal = pref.getInt("HEIGHT",160);
        height.text = heightVal.toString();
        //シークバーの現在値
        seekBar.progress = heightVal;

        seekBar.setOnSeekBarChangeListener(
            object:SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    height.text = progress.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )
    }

    override fun onPause() {
        super.onPause()
        //身長の現在値を保存する
        var pref = PreferenceManager.getDefaultSharedPreferences(this);
        pref.edit{
            //身長ラベルの表示値を取得して
            this.putInt("HEIGHT",height.texttoStrong(),toInt);
        }
    }
}
