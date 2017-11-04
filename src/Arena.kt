import personagens.*

fun batalhar(p1: Personagem, p2: Personagem){

    println("Iniciando combate entre ${p1.nome} e ${p2.nome}!")
    println("Apresentando os combatentes:")
    p1.apresentar()
    p2.apresentar()

    println("\nQUE COMECE A LUTA!\n")

    while (p1.isAlive() && p2.isAlive()) {
        if (p1.isAlive()) {
            p1.atacar(p2)
            p2.status()
        } else break

        if (p2.isAlive()) {
            p2.atacar(p1)
            p1.status()
        } else break
    }
    if (p1.isAlive()) println("O vencedor é  ${p1.nome.toUpperCase()} !")
    else println("O vencedor é ${p2.nome.toUpperCase()}!")
}

fun main(args: Array<String>) {

    val g = Guerreiro("Lula")
    val f = Feiticeiro("Cunha")

    batalhar(g, f)
}