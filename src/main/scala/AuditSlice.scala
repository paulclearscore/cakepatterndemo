trait Logger {
  def log(payload: String): Unit = {
    println("**Logger**")
    println(s"The Logger says: $payload")
  }
}

trait HttpClients {
  def create(service: String): String = {
    service + " created.\n"
  }
}

trait AuditService {
  def audit(message: String, score: Int): String

  val logger = createLogger("AuditService")

  def createLogger(tag: String): Logger = {
    new Logger {
      override def log(tag: String): Unit = super.log(tag)
    }
  }
}

trait AuditDietSlice {
  def auditService: AuditService
}

trait AuditSlice extends AuditDietSlice {
  this: HttpClients with Logger =>
  override val auditService: AuditService = new AuditService {
    override def audit(message: String, score: Int): String = {
      println("**AuditService**")
      println(create("AuditService"))
      auditService.logger.log(message + s" | Users score: ${score}")
      "Complete"
    }
  }
}
