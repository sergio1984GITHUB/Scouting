package ipca.am.scounting.main
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ipca.am.scounting.R
import ipca.am.scounting.helpers.VolleyHelper
import kotlinx.android.synthetic.main.activity_create_activities.*
import kotlinx.android.synthetic.main.activity_create_scout.*
import kotlinx.android.synthetic.main.activity_scouts_row.*
import java.time.LocalDateTime

class CreateScout : AppCompatActivity() {
    var idScout : Int? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_scout)

        btn_CreateScout.setOnClickListener {

        if (edtTex_RegisterName.text.toString() == "" || edtTex_RegisterBirthdate.text.toString() == ""
            || edtTex_RegisterEmail.text.toString() == "" || edtTex_RegisterPhone.text.toString() == ""
            ||  edtTex_RegisterCountry.text.toString() == "") {

            Toast.makeText(applicationContext,"Preencher todos campos", Toast.LENGTH_SHORT).show()
        }

        else {

            val intentResult = Intent()
            idScout = (0..400000000).random()
            val scoutName = findViewById<EditText>(R.id.edtTex_RegisterName)
            val scoutBirthdate = findViewById<EditText>(R.id.edtTex_RegisterBirthdate)
            val scoutEmail = findViewById<EditText>(R.id.edtTex_RegisterEmail)
            val scoutPhone = findViewById<EditText>(R.id.edtTex_RegisterPhone)
            val scoutCountry = findViewById<EditText>(R.id.edtTex_RegisterCountry)
            val scoutCreationDate = LocalDateTime.now()


            VolleyHelper.instance.createNewScout (

                this@CreateScout,
                idScout!!,
                scoutName.text.toString(),
                scoutBirthdate.text.toString(),
                scoutEmail.text.toString(),
                scoutPhone.text.toString(),
                scoutCountry.text.toString(),
                scoutCreationDate) { response ->

                if (response) {

                    Toast.makeText(applicationContext,"Scout criado", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, ActivityDetailScout::class.java)
                    intent.putExtra("Scout ID", idScout!!.toInt())

                    startActivity(intent)
                }

                else {
                    Toast.makeText(applicationContext,"Erro ao criar Scout", Toast.LENGTH_SHORT).show()
                }

                setResult(Activity.RESULT_OK, intentResult)
                finish()
            }
        }
    }
}




}