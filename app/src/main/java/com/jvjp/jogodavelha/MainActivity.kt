package com.jvjp.jogodavelha

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
   private var jogador: Int = 1
   private var listButtons = arrayListOf<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        listButtons = arrayListOf<Button>(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        btn_restart.visibility = View.GONE
        for (i in 0..listButtons.size-1){
            listButtons[i].setOnClickListener {
                joga(it as Button)
            }
        }
        btn_restart.setOnClickListener {
            restart()
        }
    }
    private fun joga(btn: Button){
        if (jogador % 2 != 0){
            btn.text = "X"
        }else{
            btn.text = "O"
        }
        btn.isClickable = false
        checkGame()
        ++jogador
    }
    private fun restart(){
        for(i in 0..listButtons.size-1){
            listButtons[i].text = ""
            listButtons[i].isClickable = true
        }
        tv_result.text = ""
        jogador = 1
        btn_restart.visibility = View.GONE
    }
    private fun checkGame(){
        if(jogador == 9){
            tv_result.text = "Empate"
            btn_restart.visibility = View.VISIBLE
        }
        val numb = listOf<Int>(0,3,6)
        for(x in numb){
            if (listButtons[x].text != "" && listButtons[x].text == listButtons[x+1].text && listButtons[x+1].text == listButtons[x + 2].text) {
                ganhador()
            }
        }
        for(x in 0..2){
            if(listButtons[x].text != "" && listButtons[x].text == listButtons[x+3].text && listButtons[x+3].text == listButtons[x+6].text){
                ganhador()
            }
        }
        if ((listButtons[0].text != "" && listButtons[0].text == listButtons[4].text && listButtons[4].text == listButtons[8].text)
            || (listButtons[2].text != "" && listButtons[2].text == listButtons[4].text && listButtons[4].text == listButtons[6].text)) {
            ganhador()
        }
    }
    private fun ganhador(){
        if(jogador % 2 != 0){
            tv_result.text = "Ganhador: X"
        }else{
            tv_result.text = "Ganhador: 0"
        }
        endGame()
        btn_restart.visibility = View.VISIBLE
    }
    private fun endGame(){
        for (i in 0..listButtons.size-1){
            listButtons[i].isClickable = false
        }
    }
}