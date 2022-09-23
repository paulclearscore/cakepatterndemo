object ThreeSliceCake
    extends App
    with ConfigSlice
    with ScorerSlice
    with AuditSlice
    with HttpClients
    with Logger {

  // run config
  val httpConfiguration = configImpl.runConfig()

  // run scorer
  val score = scorerService.getClearScore(httpConfiguration)

  // create audit service with logging

  val audit = auditService.audit("Audit the dietary benefits of cake", score)
  val logger = auditService.createLogger("I am a logger")
  println(audit)

}
