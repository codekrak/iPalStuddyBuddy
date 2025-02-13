import io.javalin.Javalin;

public class BackendServer {
    public static void main(String[] args) {
        // Create a new Javalin instance
        Javalin app = Javalin.create().start(7000);

        // Define a basic GET endpoint
        app.get("/hello", ctx -> ctx.result("Hello, iPal!"));
    }
}
