class VisualizationServer {
    constructor(handlers) {
        this.handlers = handlers || {}
    }

    connect() {
        this.ws = new WebSocket(socketUrl("viz"));

        this.ws.onopen = () => {
            console.log("onopen");
            // this.handlers.onOpen()
        };

        this.ws.onclose = () => {
            console.log("onclose");
            // this.handlers.onClose()
        };

        this.ws.onmessage = (message) => {
            console.log("message", message.data);
            const data = JSON.parse(message.data);
            console.log("data", data);

            if (this.handlers.onData) {
                this.handlers.onData(data);
            }
        };

        function socketUrl(relativeUrl) {
            return "ws://localhost:8080/" + relativeUrl
        }
    }
}

export default VisualizationServer;