const { ObjectID } = require("bson")
const nodemailer = require('nodemailer')

async function SendMail(userEmail, subject, content, DataHTML) {
    const transporter = nodemailer.createTransport({
        service: 'gmail',
        auth: {
            user: 'mical.garage266@gmail.com',
            pass: 'bcwshztyoymyavbk',
        },
    });
    const mailOptions = {
        from: 'mical.garage266@gmail.com',
        to: userEmail,
        subject: subject,
        text: content,
        html: DataHTML
    };

    await transporter.sendMail(mailOptions, function (error, info) {
        if (error) {
            console.log(error);
        } else {
            console.log('Email sent: ' + info.response);
        }
    });

}

const dateDiff = (date1, date2) => {
    var diff = {}
    var tmp = date2 - date1;

    tmp = Math.floor(tmp / 1000);
    diff.sec = tmp % 60;

    tmp = Math.floor((tmp - diff.sec) / 60);
    diff.min = tmp % 60;

    tmp = Math.floor((tmp - diff.min) / 60);
    diff.hour = tmp % 24;

    tmp = Math.floor((tmp - diff.hour) / 24);
    diff.day = tmp;

    return diff;
}

exports.dateDiff = dateDiff
exports.SendMail = SendMail