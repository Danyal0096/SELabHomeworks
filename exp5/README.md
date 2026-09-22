# Software Engineering Lab - Experiment 5 (Docker Fundamentals)

This experiment belongs in the existing `SELabHomeworks` repository under `exp5/`; no separate Git repository is needed.

## Results

Docker 29.6.1 and Compose 5.3.0 were verified on Windows using Docker Desktop with Linux containers / WSL 2. The server and client images built successfully; both services started; the client received five successful responses; the host received HTTP 200; the server container's `/app` directory was inspected. Screenshots are in `evidence/` and the full report is in `report/`.

**Port note:** The assignment specifies host port 8000, but Windows excluded ports 7967-8066, so host port 8300 was used instead. The server continues to listen on port 80 inside its container. The working local `docker-compose.yml` should contain `"8300:80"` rather than the initial scaffold's `"8000:80"`.

## Directory layout

```text
exp5/
├── client/
│   ├── client.py
│   └── Dockerfile
├── server/
│   ├── server.py
│   └── Dockerfile
├── docker-compose.yml
├── evidence/
│   ├── 01-build.png
│   ├── 02-deployment.png
│   ├── 03-communication.png
│   └── 04-container-inspection.png
├── report/
│   ├── REPORT.md
│   ├── Experiment_5_Docker_Report.docx
│   └── Experiment_5_Docker_Report.pdf
└── README.md
```

## Reproduce

From the `exp5` folder, verify the port mapping in `docker-compose.yml`, then run:

```powershell
docker compose config --quiet
docker compose build
docker compose up -d
docker compose ps -a
curl.exe -i http://localhost:8300
docker compose logs --no-color my-client
docker compose exec my-server sh
```

Inside the shell, run `pwd`, `ls -la`, and `exit`. The client sends five requests and exits normally (`Exited (0)`) while the server continues serving requests. Shut down when finished with `docker compose down`.

The Python code was reconstructed from partially clipped lines in the handout; original source files from the TA should take precedence if supplied. The attached screenshots are real local execution evidence, not simulated logs.

## Submission

The teaching assistant waived the video requirement. Submit the report/documentation and a public link to the existing GitHub repository after committing and pushing the experiment. Do not claim the repository is updated until the push succeeds. AI assistance is disclosed in the report.
