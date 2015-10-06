import wave
import struct
import math

task_in = wave.open("task_m.wav", "r")
task_out = wave.open("task_out.wav", "w")

rate = task_in.getframerate()

for f in [task_out]:
    f.setnchannels(1)
    f.setsampwidth(2)
    f.setframerate(rate)

A = 0.000007
freq = 2015.0

res = []
mx = 0
for n in range(0, task_in.getnframes()):
    t = n/rate

    carrier = A * math.cos(2 * math.pi * freq * t)
    signal = struct.unpack('h', task_in.readframes(1))[0] / 32768.0
    base = signal / carrier - 1

    res += [base]

    mx = max(mx, abs(base))


for n in res:
    if mx > 32767:
        n = n*32767/mx
    task_out.writeframes(struct.pack('h', n))
