# LLAMA Sentiment Analysis POC (Java)

### Run LLAMA container

```bash
sudo docker pull ollama/ollama
sudo docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
sudo docker exec -it ollama ollama run llama3
```

### Runt the Java application

Run the application and look to the console
![console-result](screenshots/ai-interaction.png)