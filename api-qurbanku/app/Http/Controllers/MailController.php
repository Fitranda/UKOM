<?php
namespace App\Http\Controllers;

use Illuminate\Http\Request;

use Illuminate\Support\Facades\Mail;


class MailController extends Controller{
    public function mail() {
        $data = array('name'=>"Pelanggan");
        Mail::send('mail', $data, function($message) {
        $message->to('tebeltipis689@gmail.com', 'Pelanggan')->subject('Test Mail from Qurbanku');
        $message->from('ramadhana689@gmail.com','Qurbanku');
        });
        echo "Email Sent. Check your inbox.";
        }
}
