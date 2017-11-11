package equipamentos

import personagens.Personagem

abstract class Arma : Equipamento() {


    abstract fun usar(atacante: Personagem, atacado: Personagem)

    fun getTipo(): String {
        when {
            this is Espada  -> return "espada"
            this is Machado -> return "machado"
            this is Cajado  -> return  "cajado"
        }
        return ""
    }


}
