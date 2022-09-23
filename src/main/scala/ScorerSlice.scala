trait Score {
  def getScore(): Int = {
    println("**Scorer**")
    650
  }
}

trait ScoreConversion {
  def convertScore(equiFax: Int): Int = {
    ((equiFax.toDouble / 1000) * 700).toInt
  }
}

trait ScorerService {
  this: Score with ScoreConversion =>
  def getClearScore(httpConfig: String): Int = {
    val rawUserScore: Int = getScore()
    println(s"Equifax score: $rawUserScore from path: $httpConfig")
    val clearScore: Int = convertScore(rawUserScore)
    println(s"ClearScore score: ${clearScore}\n")
    clearScore
  }

}

trait ScorerDietSlice {
  def scorerService: ScorerService
}

trait ScorerSlice extends ScorerDietSlice {
  override val scorerService: ScorerService = new ScorerService
    with Score
    with ScoreConversion {}
}
