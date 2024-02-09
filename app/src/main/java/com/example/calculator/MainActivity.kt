package com.example.calculator

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var hasNatija = false

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNol.setOnClickListener(View.OnClickListener {
            if (binding.txt.text != "0"){
                binding.txt.text = "${binding.txt.text}0"
            }

        })

        binding.btn1.setOnClickListener {
            raqamYozish(1)

        }

        binding.btn2.setOnClickListener {
            raqamYozish(2)

        }

        binding.btn3.setOnClickListener {
            raqamYozish(3)

        }
            binding.btn4.setOnClickListener {
            raqamYozish(4)
        }

        binding.btn5.setOnClickListener {
            raqamYozish(5)

        }

        binding.btn6.setOnClickListener {
            raqamYozish(6)

        }

        binding.btn7.setOnClickListener {
            raqamYozish(7)

        }

        binding.btn8.setOnClickListener {
            raqamYozish(8)

        }

        binding.btn9.setOnClickListener {
            raqamYozish(9)

        }

        binding.btnNuqta.setOnClickListener {
            val misol = binding.txt.text.toString()
            var amalIndex = -1
            for (i in misol.indices) {
                if (misol[i] == '+' || misol[i] == '-' || misol[i] == '*' || misol[i] == '/'){
                    amalIndex = i
                }
            }
            if (amalIndex == -1){
                if (!binding.txt.text.toString().contains('.')){
                    binding.txt.text = "${binding.txt.text}."
                }
            }else {
                val ekranLength = binding.txt.text.length
                val matn = binding.txt.text.toString().subSequence(amalIndex, ekranLength)
                if (!matn.contains('.')) {
                    binding.txt.text = "${binding.txt.text}."
                }
            }
        }

        binding.btnClear.setOnClickListener {
            binding.txt.text = "0"
            hasNatija = false

        }

        binding.btnBacksape.setOnClickListener {
            val a = binding.txt.text
            if (a.length == 1 || a == "-"){
                binding.txt.text = "0"
            }else
                binding.txt.text = a.subSequence(0, a.length-1)
        }


        binding.btnQoshish.setOnClickListener {
            val text = binding.txt.text
            if (text=="0"){

            }else amalYozish("+")
        }

        binding.btnAyrish.setOnClickListener {
            val text = binding.txt.text
            if (text=="0"){

            }else amalYozish("-")
        }

        binding.btnKopaytirish.setOnClickListener {
            val text = binding.txt.text
            if (text=="0"){

            }else amalYozish("*")
        }

        binding.btnBolish.setOnClickListener {
            val text = binding.txt.text
            if (text=="0"){

            }else amalYozish("/")
        }

        binding.btnTeng.setOnClickListener {
            val text = binding.txt.text
            if (text.endsWith("+") || text.endsWith("-") || text.endsWith("*") || text.endsWith("/") || text == "0") {

            }else if(!text.contains("+") && !text.contains("-") && !text.contains("*") && !text.contains("/")){
                binding.txt.text = text
            }else {
                if (hasNatija == false) hisoblash()

            }
        }
    }


    fun raqamYozish(raqam:Int){
            val text = binding.txt.text.toString()

            if (hasNatija) {
                binding.txt.text = "$raqam"
                hasNatija = false
            } else {

                val r = raqam.toString()
                if (binding.txt.text == "0") {
                    binding.txt.text = r
                } else {
                    binding.txt.text = "${binding.txt.text}$r"
                }
            }
            binding.scrollView.scrollBy(100, 100)
        }


    fun amalYozish(amalArg:String){
        val text = binding.txt.text
        if (text.endsWith("+") || text.endsWith("-") || text.endsWith("*") || text.endsWith("/")){

        }else {
            if (hasNatija) {
                val misol = binding.txt.text.toString()
                if (binding.txt.text.startsWith("-")){
                    binding.txt.text = "${misol.subSequence(1, misol.length)}$amalArg"
                }else {
                    for (i in misol.indices) {
                        if (misol[i] == '=') {
                            binding.txt.text = "${misol.subSequence(i + 1, misol.length)}$amalArg"
                            break
                        }
                    }
                }
                hasNatija = false
            } else {
                binding.txt.text = "${binding.txt.text}$amalArg"
            }
            binding.scrollView.scrollBy(100, 100)
        }
    }


    fun hisoblash(){

        var sonlar = ArrayList<Double>()
        var amallar = ArrayList<Int>()

        val misol = binding.txt.text.toString()

        var index1 = 0
        for (i in misol.indices) {
            if (amallar.isEmpty()) {
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            }else{
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1+1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            }
        }

        sonlar.add(misol.subSequence(index1+1, misol.length).toString().toDouble())

        var count = 0
        var natija = sonlar.first()
        while (count < amallar.size) {

            when (amallar[count]) {
                0 -> {
                    natija += sonlar[count + 1]
                }
                1 -> {
                    natija -= sonlar[count + 1]
                }
                2 -> {
                    natija *= sonlar[count + 1]
                }
                3 -> {
                    natija /= sonlar[count + 1]
                }
            }

            count++
        }

        val intNatija:Int = natija.toInt()
        if (natija / intNatija == 1.0){

            binding.txt.text = "${binding.txt.text}= $intNatija"
        }else {
            binding.txt.text = "${binding.txt.text}= $natija"
        }
        hasNatija = true
        val maxScrollAmount = binding.scrollView.maxScrollAmount
        binding.scrollView.scrollBy(maxScrollAmount, maxScrollAmount)
        binding.scrollView.scrollBy(maxScrollAmount, maxScrollAmount)
        println(maxScrollAmount)
    }
}