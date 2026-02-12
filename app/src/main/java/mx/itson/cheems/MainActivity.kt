package mx.itson.cheems

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var gameOverCard = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnRestart = findViewById<Button>(R.id.restart)
        btnRestart.setOnClickListener(this)

        start()
    }

    fun start() {
        for (i in 1..9) {
            val btnCard = findViewById<ImageButton>(
                resources.getIdentifier("card$i", "id", this.packageName)
            )
            btnCard.setOnClickListener(this)
            btnCard.setBackgroundResource(R.drawable.cheems_question)
        }

        gameOverCard = (1..9).random()
        Log.d("Valor de la carta perdedora", "la carta perdedora es $gameOverCard")
    }

    fun flip(card: Int) {
        if (card == gameOverCard) {
            Toast.makeText(this, "Perdiste jajajajajaj menso", Toast.LENGTH_LONG).show()

            for (i in 1..9) {
                val btnCard = findViewById<ImageButton>(
                    resources.getIdentifier("card$i", "id", this.packageName)
                )
                if (i == card) {
                    btnCard.setBackgroundResource(R.drawable.cheems_bad)
                } else {
                    btnCard.setBackgroundResource(R.drawable.cheems_ok)
                }
            }
        } else {
            val btnCard = findViewById<ImageButton>(
                resources.getIdentifier("card$card", "id", this.packageName)
            )
            btnCard.setBackgroundResource(R.drawable.cheems_ok)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.restart -> {start()}
            R.id.card1 -> flip(1)
            R.id.card2 -> flip(2)
            R.id.card3 -> flip(3)
            R.id.card4 -> flip(4)
            R.id.card5 -> flip(5)
            R.id.card6 -> flip(6)
            R.id.card7 -> flip(7)
            R.id.card8 -> flip(8)
            R.id.card9 -> flip(9)
        }
    }
}
