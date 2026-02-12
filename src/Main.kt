import kotlin.math.absoluteValue

val esc: String = 27.toChar().toString()
val startBlue = "$esc[30;44m"
val startGrey = "$esc[30;47m"
val startWhite = "$esc[30;30m"
val letter = "ABCDEFGHIJKLMNOPQRSTUVXWYZ"
val number = "123456789"
val end = "$esc[0m"
val pawnWhite = '\u2659'
val bishopWhite = '\u2657'
val towerWhite = '\u2656'
val queenWhite = '\u2655'
val kingWhite = '\u2654'
val horseWhite = '\u2658'
val pawnBlack = '\u265F'
val kingBlack = '\u265A'
val towerBlack = '\u265C'
val horseBlack = '\u265E'
val bishopBlack = '\u265D'
val queenBlack = '\u265B'
fun writeBlue() = "$startBlue   $end"
fun writeWhite() = "$startWhite   $end"
fun writeGrey() = "$startGrey   $end"
fun invalidInput(): String = "Invalid response."
fun checkWhiteMiddle(countByLines: Int, countByColumns: Int): Boolean {
    if (countByLines % 2 == 0 && countByColumns % 2 == 0) {
        return true
    }
    if (countByLines % 2 != 0 && countByColumns % 2 != 0) {
        return true
    }
    return false
}
fun checkGreyMiddle(countByLines: Int, countByColumns: Int): Boolean {
    if (countByLines % 2 != 0 && countByColumns % 2 == 0) {
        return true
    }
    if (countByLines % 2 == 0 && countByColumns % 2 != 0) {
        return true
    }
    return false
}
fun simpleBoard(numColumns: Int, numLines: Int): String {
    var board = ""
    var countByLines = 0
    var countByColumns = 0
    do {
        do {
            if (checkWhiteMiddle(countByLines, countByColumns)) {
                board += writeWhite()
            }
            if (checkGreyMiddle(countByLines, countByColumns)) {
                board += writeGrey()
            }
            countByColumns++
        } while (countByColumns < numColumns)
        countByColumns = 0
        countByLines++
        board += "\n"
    } while (countByLines < numLines)
    return board
}
fun legendBoard(numColumns: Int, numLines: Int): String {
    val columns = numColumns + 1
    val lines = numLines + 1
    var countByLines = 0
    var countByColumns = 0
    var board = ""
    do {
        do {
            if (countByLines == lines || (countByLines == 0 && countByColumns == 0) || countByColumns == columns) {
                board += writeBlue()
            }
            if (countByLines > 0 && countByLines != lines && countByColumns == 0) {
                board += ("$startBlue ${number[-1 + countByLines]} $end")
            }
            if (countByColumns > 0 && countByColumns != columns && countByLines == 0) {
                board += ("$startBlue ${letter[-1 + countByColumns]} $end")
            }
            if ((countByLines > 0 && countByLines < lines) && countByColumns > 0 && countByColumns < columns) {
                if (checkWhiteMiddle(countByLines, countByColumns)) {
                    board += writeWhite()
                }
                if (checkGreyMiddle(countByLines, countByColumns)) {
                    board += writeGrey()
                }
            }
            countByColumns++
        } while (countByColumns < columns + 1)
        countByColumns = 0
        countByLines++
        board += "\n"
    } while (countByLines < lines + 1)
    return board
}
fun piecesBoard(numColumns: Int, numLines: Int, pieces: Array<Pair<String, String>?>): String {
    var board = ""
    var countByLines = 0
    var countByColumns = 0
    var iterador = 0
    do {
        do {
            if (checkWhiteMiddle(countByLines, countByColumns)) {
                if (pieces[iterador] == null) {
                    board += writeWhite()
                } else {
                    board += "$startWhite ${convertStringToUnicode(
                        pieces[iterador]!!.first, pieces[iterador]!!.second
                    )} $end"
                }
            }
            if (checkGreyMiddle(countByLines, countByColumns)) {
                if (pieces[iterador] == null) {
                    board += writeGrey()
                } else {
                    board += "$startGrey ${convertStringToUnicode(
                        pieces[iterador]!!.first, pieces[iterador]!!.second
                    )} $end"
                }
            }
            countByColumns++
            iterador++
        } while (countByColumns < numColumns)
        countByColumns = 0
        countByLines++
        board += "\n"
    } while (countByLines < numLines)
    return board
}
fun completeBoard(numColumns: Int, numLines: Int, pieces: Array<Pair<String, String>?>): String {
    var board = ""
    val columns = numColumns + 1
    val lines = numLines + 1
    var countByLines = 0
    var countByColumns = 0
    var iterador = 0
    do {
        do {
            if (countByLines == lines || (countByLines == 0 && countByColumns == 0) || countByColumns == columns) {
                board += writeBlue()
            }
            if (countByLines > 0 && countByLines != lines && countByColumns == 0) {
                board += ("$esc[30;44m ${number[-1 + countByLines]} $end")
            }
            if (countByColumns > 0 && countByColumns != columns && countByLines == 0) {
                board += ("$esc[30;44m ${letter[-1 + countByColumns]} $end")
            }
            if ((countByLines > 0 && countByLines < lines) && countByColumns > 0 && countByColumns < columns) {
                if (checkWhiteMiddle(countByLines, countByColumns)) {
                    if (pieces[iterador] == null) {
                        board += writeWhite()
                        iterador++
                    } else {
                        board += "$startWhite ${convertStringToUnicode(
                            pieces[iterador]!!.first, pieces[iterador]!!.second
                        )} $end"
                        iterador++
                    }
                }
                if (checkGreyMiddle(countByLines, countByColumns)) {
                    if (pieces[iterador] == null) {
                        board += writeGrey()
                        iterador++
                    } else {
                        board += "$startGrey ${convertStringToUnicode(
                            pieces[iterador]!!.first, pieces[iterador]!!.second
                        )} $end"
                        iterador++
                    }
                }
            }
            countByColumns++

        } while (countByColumns < columns + 1)
        countByColumns = 0
        countByLines++
        board += "\n"
    } while (countByLines < lines + 1)
    return board
}
fun buildBoard(
    numColumns: Int,
    numLines: Int,
    showLegend: Boolean = false,
    showPieces: Boolean = false,
    pieces: Array<Pair<String, String>?>
): String {
    var board: String
    if (!showLegend && !showPieces) {
        board = simpleBoard(numColumns, numLines)
    } else if (showLegend && !showPieces) {
        board = legendBoard(numColumns, numLines)
    } else if (!showLegend && showPieces) {
        board = piecesBoard(numColumns, numLines, pieces)
    } else {
        board = completeBoard(numColumns, numLines, pieces)
    }
    return board
}
fun pieces8x8(numColumns: Int, numLines: Int): Array<Pair<String, String>?> {
    val tabuleiroPecas: Array<Pair<String, String>?> = arrayOfNulls(numColumns * numLines)
    for (posicao in 0..tabuleiroPecas.size - 1) {
        if (posicao == 0 || posicao == 7) {
            tabuleiroPecas[posicao] = Pair("T", "b")
        }
        if (posicao == 1 || posicao == 6) {
            tabuleiroPecas[posicao] = Pair("H", "b")
        }
        if (posicao == 2 || posicao == 5) {
            tabuleiroPecas[posicao] = Pair("B", "b")
        }
        if (posicao == 3) {
            tabuleiroPecas[posicao] = Pair("Q", "b")
        }
        if (posicao == 4) {
            tabuleiroPecas[posicao] = Pair("K", "b")
        }
        if (posicao > 7 && posicao < 16) {
            tabuleiroPecas[posicao] = Pair("P", "b")
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (posicao == 63 || posicao == 56) {
            tabuleiroPecas[posicao] = Pair("T", "w")
        }
        if (posicao == 57 || posicao == 62) {
            tabuleiroPecas[posicao] = Pair("H", "w")
        }
        if (posicao == 58 || posicao == 61) {
            tabuleiroPecas[posicao] = Pair("B", "w")
        }
        if (posicao == 59) {
            tabuleiroPecas[posicao] = Pair("K", "w")
        }
        if (posicao == 60) {
            tabuleiroPecas[posicao] = Pair("Q", "w")
        }
        if (posicao < 56 && posicao > 47) {
            tabuleiroPecas[posicao] = Pair("P", "w")
        }
    }
    return tabuleiroPecas
}
fun pieces7x7(numColumns: Int, numLines: Int): Array<Pair<String, String>?> {
    val tabuleiroPecas: Array<Pair<String, String>?> = arrayOfNulls(numColumns * numLines)
    for (posicao in 0..tabuleiroPecas.size - 1) {
        if (posicao == 0 || posicao == 6) {
            tabuleiroPecas[posicao] = Pair("T", "b")
        }
        if (posicao == 1 || posicao == 5) {
            tabuleiroPecas[posicao] = Pair("H", "b")
        }
        if (posicao == 2 || posicao == 4) {
            tabuleiroPecas[posicao] = Pair("B", "b")
        }
        if (posicao == 3) {
            tabuleiroPecas[posicao] = Pair("K", "b")
        }
        if (posicao > 6 && posicao < 14) {
            tabuleiroPecas[posicao] = Pair("P", "b")
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (posicao == 42 || posicao == 48) {
            tabuleiroPecas[posicao] = Pair("T", "w")
        }
        if (posicao == 43 || posicao == 47) {
            tabuleiroPecas[posicao] = Pair("H", "w")
        }
        if (posicao == 44 || posicao == 46) {
            tabuleiroPecas[posicao] = Pair("B", "w")
        }
        if (posicao == 45) {
            tabuleiroPecas[posicao] = Pair("K", "w")
        }
        if (posicao < 42 && posicao > 34) {
            tabuleiroPecas[posicao] = Pair("P", "w")
        }
    }
    return tabuleiroPecas
}
fun pieces6x7(numColumns: Int, numLines: Int): Array<Pair<String, String>?> {
    val tabuleiroPecas: Array<Pair<String, String>?> = arrayOfNulls(numColumns * numLines)
    for (posicao in 0..tabuleiroPecas.size - 1) {
        if (posicao == 0) {
            tabuleiroPecas[posicao] = Pair("T", "b")
        }
        if (posicao == 5) {
            tabuleiroPecas[posicao] = Pair("H", "b")
        }
        if (posicao == 1 || posicao == 4) {
            tabuleiroPecas[posicao] = Pair("B", "b")
        }
        if (posicao == 2) {
            tabuleiroPecas[posicao] = Pair("Q", "b")
        }
        if (posicao == 3) {
            tabuleiroPecas[posicao] = Pair("K", "b")
        }
        if (posicao > 5 && posicao < 12) {
            tabuleiroPecas[posicao] = Pair("P", "b")
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (posicao == 36) {
            tabuleiroPecas[posicao] = Pair("T", "w")
        }
        if (posicao == 41) {
            tabuleiroPecas[posicao] = Pair("H", "w")
        }
        if (posicao == 37 || posicao == 40) {
            tabuleiroPecas[posicao] = Pair("B", "w")
        }
        if (posicao == 38) {
            tabuleiroPecas[posicao] = Pair("K", "w")
        }
        if (posicao == 39) {
            tabuleiroPecas[posicao] = Pair("Q", "w")
        }
        if (posicao < 36 && posicao > 29) {
            tabuleiroPecas[posicao] = Pair("P", "w")
        }
    }
    return tabuleiroPecas
}

fun pieces6x6(numColumns: Int, numLines: Int): Array<Pair<String, String>?> {
    val tabuleiroPecas: Array<Pair<String, String>?> = arrayOfNulls(numColumns * numLines)
    for (posicao in 0..tabuleiroPecas.size - 1) {
        if (posicao == 0) {
            tabuleiroPecas[posicao] = Pair("H", "b")
        }
        if (posicao == 1 || posicao == 4) {
            tabuleiroPecas[posicao] = Pair("B", "b")
        }
        if (posicao == 2) {
            tabuleiroPecas[posicao] = Pair("Q", "b")
        }
        if (posicao == 3) {
            tabuleiroPecas[posicao] = Pair("K", "b")
        }
        if (posicao == 5) {
            tabuleiroPecas[posicao] = Pair("T", "b")
        }
        if (posicao > 5 && posicao < 12) {
            tabuleiroPecas[posicao] = Pair("P", "b")
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (posicao == 30) {
            tabuleiroPecas[posicao] = Pair("H", "w")
        }
        if (posicao == 31 || posicao == 34) {
            tabuleiroPecas[posicao] = Pair("B", "w")
        }
        if (posicao == 33) {
            tabuleiroPecas[posicao] = Pair("Q", "w")
        }
        if (posicao == 32) {
            tabuleiroPecas[posicao] = Pair("K", "w")
        }
        if (posicao == 35) {
            tabuleiroPecas[posicao] = Pair("T", "w")
        }
        if (posicao < 30 && posicao > 23) {
            tabuleiroPecas[posicao] = Pair("P", "w")
        }
    }
    return tabuleiroPecas
}

fun pieces4x4(numColumns: Int, numLines: Int): Array<Pair<String, String>?> {
    val tabuleiroPecas: Array<Pair<String, String>?> = arrayOfNulls(numColumns * numLines)
    for (posicao in 0..tabuleiroPecas.size - 1) {
        if (posicao == 2) {
            tabuleiroPecas[posicao] = Pair("T", "b")
        }
        if (posicao == 3) {
            tabuleiroPecas[posicao] = Pair("B", "b")
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (posicao == 12) {
            tabuleiroPecas[posicao] = Pair("T", "w")
        }
        if (posicao == 13) {
            tabuleiroPecas[posicao] = Pair("Q", "w")
        }
    }
    return tabuleiroPecas
}

fun createInitialBoard(numColumns: Int, numLines: Int): Array<Pair<String, String>?> {
    var tabuleiroPecas: Array<Pair<String, String>?> = emptyArray()
    if (numColumns == 8 && numLines == 8) {
        tabuleiroPecas = pieces8x8(numColumns, numLines)
    }
    if (numColumns == 7 && numLines == 7) {
        tabuleiroPecas = pieces7x7(numColumns, numLines)
    }
    if (numColumns == 6 && numLines == 6) {
        tabuleiroPecas = pieces6x6(numColumns, numLines)
    }
    if (numColumns == 6 && numLines == 7) {
        tabuleiroPecas = pieces6x7(numColumns, numLines)
    }
    if (numColumns == 4 && numLines == 4) {
        tabuleiroPecas = pieces4x4(numColumns, numLines)
    }
    return tabuleiroPecas
}

fun createTotalPiecesAndTurn(numColumns: Int, numLines: Int): Array<Int?> {
    var pecasTurnos: Array<Int?> = emptyArray()
    if (numColumns == 8 && numLines == 8) {
        pecasTurnos = arrayOf(16, 16, 0)
    }
    if (numColumns == 7 && numLines == 7) {
        pecasTurnos = arrayOf(14, 14, 0)
    }
    if (numColumns == 6 && numLines == 7) {
        pecasTurnos = arrayOf(12, 12, 0)
    }
    if (numColumns == 6 && numLines == 6) {
        pecasTurnos = arrayOf(12, 12, 0)
    }
    if (numColumns == 4 && numLines == 4) {
        pecasTurnos = arrayOf(2, 2, 0)
    }
    return pecasTurnos
}

fun convertStringToUnicode(piece: String, color: String): String {
    if (piece == "T" && color == "w") {
        return "$towerWhite"
    }
    if (piece == "H" && color == "w") {
        return "$horseWhite"
    }
    if (piece == "B" && color == "w") {
        return "$bishopWhite"
    }
    if (piece == "Q" && color == "w") {
        return "$queenWhite"
    }
    if (piece == "K" && color == "w") {
        return "$kingWhite"
    }
    if (piece == "P" && color == "w") {
        return "$pawnWhite"
    }
    ///////////////////////////////////////////////////////////////////////////////////
    if (piece == "T" && color == "b") {
        return "$towerBlack"
    }
    if (piece == "H" && color == "b") {
        return "$horseBlack"
    }
    if (piece == "B" && color == "b") {
        return "$bishopBlack"
    }
    if (piece == "Q" && color == "b") {
        return "$queenBlack"
    }
    if (piece == "K" && color == "b") {
        return "$kingBlack"
    }
    if (piece == "P" && color == "b") {
        return "$pawnBlack"
    }
    return " "
}

fun getCoordinates(readText: String?): Pair<Int, Int>? {
    var coordinates: Pair<Int, Int>? = Pair(0, 0)
    if (readText != null) {
        if (readText.length != 2) {
            return null
        }
        if (readText[0].toInt() < 48 && readText[0].toInt() > 56) {
            return null
        }
        if ((readText[1].toInt() < 65 && readText[1].toInt() > 72) || (readText[1].toInt() < 97 && readText[1].toInt() > 104)) {
            return null
        }
        if (readText[1].toInt() >= 65 && readText[1].toInt() <= 72) {
            coordinates = Pair(readText[0].toInt() - 48, readText[1].toInt() - 64)
        } else if (readText[1].toInt() >= 97 && readText[1].toInt() <= 104) {
            coordinates = Pair(readText[0].toInt() - 48, readText[1].toInt() - 96)
        }
        return coordinates
    }
    return null
}

fun checkRightPieceSelected(pieceColor: String, turn: Int): Boolean {
    if (pieceColor == "w" && turn == 0) {
        return true
    }
    if (pieceColor == "b" && turn == 1) {
        return true
    }

    return false
}

fun isCoordinateInsideChess(coord: Pair<Int, Int>, numColumns: Int, numLines: Int): Boolean {
    if (coord.first > numColumns || coord.second > numLines) {
        return false
    }
    if (coord.first < 1 || coord.second < 1) {
        return false
    }
    return true
}

fun isValidTargetPiece(
    currentSelectedPiece: Pair<String, String>,
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    if (currentSelectedPiece.first == "T") {
        if (!isTowerValid(currentCoord, targetCoord, pieces, numColumns, numLines)) {
            return false
        }
    }
    if (currentSelectedPiece.first == "H") {
        if (!isHorseValid(currentCoord, targetCoord, pieces, numColumns, numLines)) {
            return false
        }
    }
    if (currentSelectedPiece.first == "B") {
        if (!isBishopValid(currentCoord, targetCoord, pieces, numColumns, numLines)) {
            return false
        }
    }
    if (currentSelectedPiece.first == "Q") {
        if (!isQueenValid(currentCoord, targetCoord, pieces, numColumns, numLines)) {
            return false
        }
    }
    if (currentSelectedPiece.first == "K") {
        if (!isKingValid(currentCoord, targetCoord, pieces, numColumns, numLines)) {
            return false
        }
    }
    if (currentSelectedPiece.first == "P") {
        if (!isKnightValid(currentCoord, targetCoord, pieces, numColumns, numLines)) {
            return false
        }
    }
    return true
}

fun movePiece(
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int,
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    totalPiecesAndTurn: Array<Int>
): Boolean {
    val posicaoInicial = currentCoord.first - 1 + ((currentCoord.second - 1) * numColumns)
    val posicaoFinal = targetCoord.first - 1 + ((targetCoord.second - 1) * numColumns)
    val pecaEscolhida = pieces[posicaoInicial]
    if (pecaEscolhida == null) {
        return false
    }
    if (isCoordinateInsideChess(currentCoord, numColumns, numLines) == false) {
        return false
    }
    if (isCoordinateInsideChess(targetCoord, numColumns, numLines) == false) {
        return false
    }
    if (pieces[posicaoInicial] == null) {
        return false
    }
    val pecaSemNull = pieces[posicaoInicial] as Pair<String, String>

    if (checkRightPieceSelected(pecaSemNull.second, totalPiecesAndTurn[2]) == false) {
        return false
    }
    if (pieces[posicaoFinal] != null) {
        if (pieces[posicaoFinal]!!.second == pieces[posicaoInicial]!!.second) {
            return false
        }
    }
    if (!isValidTargetPiece(pecaEscolhida, currentCoord, targetCoord, pieces, numColumns, numLines)) {
        return false
    }
    if (totalPiecesAndTurn[2] == 0 && pieces[posicaoFinal] != null) {
        if (pieces[posicaoFinal]!!.second == "b") {
            totalPiecesAndTurn[1]--
            pieces[posicaoFinal] = pieces[posicaoInicial]
            pieces[posicaoInicial] = null
        }
    }
    if (totalPiecesAndTurn[2] == 0 && pieces[posicaoFinal] == null) {
        pieces[posicaoFinal] = pieces[posicaoInicial]
        pieces[posicaoInicial] = null
    }
    if (totalPiecesAndTurn[2] == 1 && pieces[posicaoFinal] != null) {
        if (pieces[posicaoFinal]!!.second == "w") {
            totalPiecesAndTurn[0]--
            pieces[posicaoFinal] = pieces[posicaoInicial]
            pieces[posicaoInicial] = null
        }
    }
    if (totalPiecesAndTurn[2] == 1 && pieces[posicaoFinal] == null) {
        pieces[posicaoFinal] = pieces[posicaoInicial]
        pieces[posicaoInicial] = null
    }
    when (totalPiecesAndTurn[2]) {
        0 -> totalPiecesAndTurn[2] = 1
        1 -> totalPiecesAndTurn[2] = 0
    }
    return true
}

fun startNewGame(
    whitePlayer: String,
    blackPlayer: String,
    pieces: Array<Pair<String, String>?>,
    totalPiecesAndTurn: Array<Int?>,
    numColumns: Int,
    numLines: Int,
    showLegend: Boolean = false,
    showPieces: Boolean = false
) {
    var firstPosition: String?
    var secondPosition: String? = ""
    var targetCoord = Pair(2, 2)
    var currentCoord = Pair(1, 2)
    totalPiecesAndTurn as Array<Int>
    do {
        println(buildBoard(numColumns, numLines, showLegend, showPieces, pieces))
        if (totalPiecesAndTurn[2] == 0) {
            println("$whitePlayer, choose a piece (e.g 2D).\n"+"Menu-> m;\n")
        }
        if (totalPiecesAndTurn[2] == 1) {
            println("$blackPlayer, choose a piece (e.g 2D).\n"+"Menu-> m;\n")
        }
        firstPosition = readLine()
        if (firstPosition != "m") {
            currentCoord = getCoordinates(firstPosition) as Pair<Int, Int>
            if (totalPiecesAndTurn[2] == 0) {
                println("$whitePlayer, choose a target piece (e.g 2D).\n"+"Menu-> m;\n")
            }
            if (totalPiecesAndTurn[2] == 1) {
                println("$blackPlayer, choose a target piece (e.g 2D).\n"+"Menu-> m;\n")
            }
            secondPosition = readLine()
            if (secondPosition != "m") {
                targetCoord = getCoordinates(secondPosition) as Pair<Int, Int>
                if (!movePiece(
                        pieces,
                        numColumns,
                        numLines,
                        currentCoord,
                        targetCoord,
                        totalPiecesAndTurn
                    )
                ) {
                    println(invalidInput())
                } else {
                    movePiece(
                        pieces,
                        numColumns,
                        numLines,
                        currentCoord,
                        targetCoord,
                        totalPiecesAndTurn
                    )
                }
            }

        }

    } while (totalPiecesAndTurn[0] != 0 && totalPiecesAndTurn[1] != 0 && firstPosition != "m" && secondPosition != "m")
}

fun isHorseValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    val posicaoInicial = currentCoord.first - 1 + ((currentCoord.second - 1) * numColumns)
    val posicaoFinal = targetCoord.first - 1 + ((targetCoord.second - 1) * numColumns)
    if (pieces[posicaoInicial] == null) {
        return false
    }
    if (pieces[posicaoFinal] != null) {
        if (pieces[posicaoFinal]!!.second == pieces[posicaoInicial]!!.second) {
            return false
        }
    }
    if (currentCoord.second - 1 == targetCoord.second) {
        if (currentCoord.first - 2 == targetCoord.first || currentCoord.first + 2 == targetCoord.first) {
            return true
        }
    }
    if (currentCoord.second - 2 == targetCoord.second) {
        if (currentCoord.first - 1 == targetCoord.first || currentCoord.first + 1 == targetCoord.first) {
            return true
        }
    }
    if (currentCoord.second + 1 == targetCoord.second) {
        if (currentCoord.first - 2 == targetCoord.first || currentCoord.first + 2 == targetCoord.first) {
            return true
        }
    }
    if (currentCoord.second + 2 == targetCoord.second) {
        if (currentCoord.first - 1 == targetCoord.first || currentCoord.first + 1 == targetCoord.first) {
            return true
        }
    }
    return false
}

fun isKingValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    val posicaoInicial = currentCoord.first - 1 + ((currentCoord.second - 1) * numColumns)
    val posicaoFinal = targetCoord.first - 1 + ((targetCoord.second - 1) * numColumns)
    if (currentCoord.first + 1 < targetCoord.first && currentCoord.first - 1 > targetCoord.first) {
        return false
    }
    if (currentCoord.second + 1 < targetCoord.second && currentCoord.second - 1 > targetCoord.second) {
        return false
    }
    if (pieces[posicaoInicial] == null) {
        return false
    }
    if (pieces[posicaoFinal] != null) {
        if (pieces[posicaoFinal]!!.second == pieces[posicaoInicial]!!.second) {
            return false
        }
    }
    return true
}

fun isTowerValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    val posicaoInicial = currentCoord.first - 1 + ((currentCoord.second - 1) * numColumns)
    val posicaoFinal = targetCoord.first - 1 + ((targetCoord.second - 1) * numColumns)
    if (currentCoord.first != targetCoord.first && currentCoord.second != targetCoord.second) {
        return false
    }
    if (pieces[posicaoFinal] == null) {
        return true
    }
    if (pieces[posicaoInicial] == null) {
        return false
    }
    if (pieces[posicaoFinal] != null) {
        if (pieces[posicaoFinal]!!.second == pieces[posicaoInicial]!!.second) {
            return false
        }
    }
    return true
}

fun isBishopValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    val posicaoInicial = currentCoord.first - 1 + ((currentCoord.second - 1) * numColumns)
    val posicaoFinal = targetCoord.first - 1 + ((targetCoord.second - 1) * numColumns)
    if (pieces[posicaoInicial] == null) {
        return false
    }
    if (pieces[posicaoFinal] != null) {
        if (pieces[posicaoFinal]!!.second == pieces[posicaoInicial]!!.second) {
            return false
        }
    }
    if ((targetCoord.second - currentCoord.second).absoluteValue != (targetCoord.first - currentCoord.first).absoluteValue) {
        return false
    }
    return true
}

fun isQueenValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    val posicaoInicial = currentCoord.first - 1 + ((currentCoord.second - 1) * numColumns)
    val posicaoFinal = targetCoord.first - 1 + ((targetCoord.second - 1) * numColumns)
    if (pieces[posicaoInicial] == null) {
        return false
    }
    if (pieces[posicaoFinal] != null) {
        if (pieces[posicaoFinal]!!.second == pieces[posicaoInicial]!!.second) {
            return false
        }
    }
    if ((targetCoord.second - currentCoord.second).absoluteValue == (targetCoord.first - currentCoord.first).absoluteValue) {
        return true
    }
    if ((currentCoord.first != targetCoord.first && currentCoord.second == targetCoord.second)) {
        return true
    }
    if (currentCoord.second != targetCoord.second && currentCoord.first == targetCoord.first) {
        return true
    }
    return false
}

fun isKnightValid(
    currentCoord: Pair<Int, Int>,
    targetCoord: Pair<Int, Int>,
    pieces: Array<Pair<String, String>?>,
    numColumns: Int,
    numLines: Int
): Boolean {
    val posicaoInicial = currentCoord.first - 1 + ((currentCoord.second - 1) * numColumns)
    val posicaoFinal = targetCoord.first - 1 + ((targetCoord.second - 1) * numColumns)
    if (pieces[posicaoInicial] == null) {
        return false
    }
    if (pieces[posicaoFinal] != null) {
        if (pieces[posicaoFinal]!!.second == pieces[posicaoInicial]!!.second) {
            return false
        }
    }
    if (currentCoord.first != targetCoord.first) {
        return false
    }
    if (currentCoord.second != targetCoord.second - 1 && currentCoord.second != targetCoord.second + 1) {
        return false
    }
    return true
}

fun buildMenu(): String = "1-> Start New Game;\n2-> Exit Game.\n"
fun checkName(number: String): Boolean {
    var count = 1
    if (number == "" || number.length < 2) {
        return false
    }
    if (number[0] in 'A'..'Z') {
        do {
            if (number[count] in 'a'..'z' || number[count] == ' ') {
            } else {
                return false
            }
            count += 1
            if (count == number.length) {
                return false
            }
        } while (number[count - 1] != ' ')
    } else {
        return false
    }
    if (count == number.length) {
        return false
    }
    if (number[count] in 'A'..'Z') {
        count += 1
        do {
            if (number[count] in 'a'..'z') {
            } else {
                return false
            }
            count += 1
        } while (count < number.length)
    } else {
        return false
    }
    return true
}

fun checkIsNumber(number: String): Boolean {
    if (number == "") {
        return false
    }
    var count = 0
    do {
        if (number[count] in '0'..'9') {
            count += 1
        } else {
            return false
        }
    } while (count < number.length)
    return true
}

fun showChessLegendOrPieces(message: String): Boolean? {
    return if (message == "Y" || message == "y") {
        true
    } else if (message == "N" || message == "n") {
        false
    } else {
        null
    }
}

fun validationLines(numLines: Int): Boolean {
    if (numLines == 8) {
        return true
    }
    if (numLines == 7) {
        return true
    }
    if (numLines == 6) {
        return true
    }
    if (numLines == 4) {
        return true
    }
    return false
}

fun validationColumns(numColumns: Int): Boolean {
    if (numColumns == 8) {
        return true
    }
    if (numColumns == 7) {
        return true
    }
    if (numColumns == 6) {
        return true
    }
    if (numColumns == 4) {
        return true
    }
    return false
}

fun validationBoard(numColumns: Int, numLines: Int): Boolean {
    if (numColumns == 8 && numLines == 8) {
        return true
    }
    if (numColumns == 7 && numLines == 7) {
        return true
    }
    if (numColumns == 6 && numLines == 6) {
        return true
    }
    if (numColumns == 6 && numLines == 7) {
        return true
    }
    if (numColumns == 4 && numLines == 4) {
        return true
    }
    return false
}

fun main() {
    var whitePlayer: String
    var blackPlayer: String
    var numColumns: Int
    var numLines: Int
    var showLegends: Boolean?
    var showPieces: Boolean?
    var pieces: Array<Pair<String, String>?>
    println("Welcome to the Chess Board Game!")
    val menu = buildMenu()
    do {
        println(menu)
        var optionMenu = readLine() ?: "2"
        if (optionMenu != "1" && optionMenu != "2") {
            println(invalidInput())
        }
        if (optionMenu == "1") {
            do {
                println("First player name?\n")
                whitePlayer = readLine().toString()
                val checkwhitePlayer: Boolean = checkName(whitePlayer)
                if (!checkwhitePlayer) println(invalidInput())
            } while (!checkwhitePlayer)
            do {
                println("Second player name?\n")
                blackPlayer = readLine().toString()
                val checkblackPlayer: Boolean = checkName(blackPlayer)
                if (!checkblackPlayer) println(invalidInput())
            } while (!checkblackPlayer)
            do {
                do {
                    println("How many chess columns?\n")
                    numColumns = readLine()!!.toInt()
                    if (!checkIsNumber(numColumns.toString())) {
                        println(invalidInput())
                    }
                    println("How many chess lines?\n")
                    numLines = readLine()!!.toInt()
                    if (!checkIsNumber(numLines.toString())) {
                        println(invalidInput())
                    }
                    if (!validationColumns(numColumns) || !validationLines(numLines)) {
                        println(invalidInput())
                    }
                } while (!validationColumns(numColumns) || !validationLines(numLines))
            } while (validationBoard(numColumns, numLines) == false)
            pieces = createInitialBoard(numColumns, numLines)
            do {
                println("Show legend (y/n)?\n")
                val checkLegends = readLine()!!.toString()
                showLegends = showChessLegendOrPieces(checkLegends)
                if (showLegends == null) {
                    println(invalidInput())
                }
            } while (showLegends == null)
            do {
                println("Show pieces (y/n)?\n")
                val checkPieces = readLine()!!.toString()
                showPieces = showChessLegendOrPieces(checkPieces)
                if (showPieces == null) {
                    println(invalidInput())
                }
            } while (showPieces == null)
            val totalPiecesAndTurn = createTotalPiecesAndTurn(numColumns, numLines)
            startNewGame(
                whitePlayer,
                blackPlayer,
                pieces,
                totalPiecesAndTurn,
                numColumns,
                numLines,
                showLegends,
                showPieces
            )
            if (totalPiecesAndTurn[0] == 0) {
                println("Congrats! $blackPlayer wins!")
            }
            if (totalPiecesAndTurn[1] == 0) {
                println("Congrats! $whitePlayer wins!")
            }
            println(menu)
            optionMenu = readLine() ?: "2"
        }
    } while (optionMenu != "2")
}