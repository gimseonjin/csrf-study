# === 메시지 구성 ===
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pathlib import Path

# ==== 설정 ====
GMAIL_USER = "gimseoniin616@gmail.com"          # 발신자 Gmail
APP_PASSWORD = "vomi imtr oxlk qusy"           # 앱 비밀번호 (16자리)
TO_EMAIL = "gimseoniin616@gmail.com"        # 수신자 이메일
SUBJECT = "📛 너의 데이터는 내가 가지고 있다"

# ==== HTML 로딩 ====
html_path = Path("ransom-note.html")  # 랜섬 메일 HTML 파일 경로
html_content = html_path.read_text(encoding="utf-8")

# ==== 이메일 구성 ====
msg = MIMEMultipart("alternative")
msg["Subject"] = SUBJECT
msg["From"] = GMAIL_USER
msg["To"] = TO_EMAIL

msg.attach(MIMEText(html_content, "html"))

# ==== 이메일 전송 ====
try:
    with smtplib.SMTP_SSL("smtp.gmail.com", 465) as server:
        server.login(GMAIL_USER, APP_PASSWORD)
        server.sendmail(GMAIL_USER, TO_EMAIL, msg.as_string())
    print("✅ 랜섬 메일 전송 완료")
except Exception as e:
    print(f"❌ 메일 전송 실패: {e}")
