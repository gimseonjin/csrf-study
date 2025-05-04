import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from pathlib import Path

# === 설정 ===
gmail_user = "gimseoniin616@gmail.com"
app_password = "vomi imtr oxlk qusy"  # Gmail 앱 비밀번호
to_email = "gimseoniin616@gmail.com"   # 대상자 이메일
subject = "🎉 MiniIAM 1주년 이벤트 초대장"
html_path = Path("csrf-invite.html")  # 로컬 HTML 파일 경로

# === 메시지 구성 ===
msg = MIMEMultipart("alternative")
msg["Subject"] = subject
msg["From"] = gmail_user
msg["To"] = to_email

html_content = html_path.read_text(encoding="utf-8")
msg.attach(MIMEText(html_content, "html"))

# === 메일 전송 ===
with smtplib.SMTP_SSL("smtp.gmail.com", 465) as server:
    server.login(gmail_user, app_password)
    server.sendmail(gmail_user, to_email, msg.as_string())

print("✅ 이메일 전송 완료")
