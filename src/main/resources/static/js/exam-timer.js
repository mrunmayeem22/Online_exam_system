const examData = document.getElementById("examData");
const examDuration = parseInt(examData.dataset.duration); 

let timeLeft = examDuration * 60;

function startTimer() {
    const timerElement = document.getElementById("timer");

    const interval = setInterval(() => {
        let minutes = Math.floor(timeLeft / 60);
        let seconds = timeLeft % 60;

        timerElement.innerHTML =
            minutes + ":" + (seconds < 10 ? "0" + seconds : seconds);

        timeLeft--;

        if (timeLeft < 0) {
            clearInterval(interval);
            alert("Time is up! Exam will be submitted.");
            document.getElementById("examForm").submit();
        }
    }, 1000);
}

startTimer();
