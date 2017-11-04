package equipamentos
import personagens.*
import java.util.*

class Arma() {

    private lateinit var dono: Personagem

    private val ESPADA          = 1
    private val DANO_ESPADA     = 15f

    private val MACHADO         = 2
    private val DANO_MACHADO    = 20f

    private val CAJADO          = 3
    private val DANO_CAJADO     = 12f

    private val BASTAO          = 4
    private val DANO_BASTAO     = 7f

    private var random = Random()
    private var tipo: Int = 0

    private var durabilidade    = 0f
    private var dano            = 0f
    private var precisao        = 0f


    constructor(dono: Personagem) : this() {
        this.dono = dono
        if (dono is Guerreiro) {
            when (getChance(3)) {
                1 -> {
                    this.dano = DANO_ESPADA
                    this.tipo = ESPADA
                }
                2 -> {
                    this.dano = DANO_MACHADO
                    this.tipo = MACHADO
                }
                else -> {
                    this.dano = DANO_BASTAO
                    this.tipo = BASTAO
                }
            }
        } else if (dono is Feiticeiro) {
            when (getChance(3)) {
                1 -> {
                    this.dano = DANO_CAJADO
                    this.tipo = CAJADO
                }
            /*2 -> {
                this.dano = DANO_MACHADO
                this.tipo = MACHADO
            }*/
                else -> {
                    this.dano = DANO_BASTAO
                    this.tipo = BASTAO
                }
            }
        }
    }


    fun usar(): Float {
        when (tipo){
            ESPADA  -> print("${dono.nome} desferiu um golpe de espada!")
            MACHADO -> print("${dono.nome} atacou com machado!")
            CAJADO  -> print("${dono.nome} atacou com magia de cajado!")
            BASTAO  -> print("${dono.nome} golpeou com bastão!")
        }

        return dano
    }

    private fun getChance(bound: Int): Int {
        return 1 + random.nextInt()%bound
    }

}