import subprocess
import time

# Serial của thiết bị
device_serial = "2aac8ba5"

# Danh sách các lệnh ADB cần thực hiện
commands = [
    f'adb -s {device_serial} shell input tap 350 1000',
    f'adb -s {device_serial} shell input tap 1050 1920',
    f'adb -s {device_serial} shell input tap 610 700',
    f'adb -s {device_serial} shell input text "1"',
    f'adb -s {device_serial} shell input tap 610 1800',
    f'adb -s {device_serial} shell input tap 610 2300',
]

# Chạy từng lệnh
for cmd in commands:
    print(f"Running: {cmd}")
    subprocess.run(cmd, shell=True)
    time.sleep(1)  # nghỉ 1 giây giữa các lệnh để đảm bảo thao tác ổn định
