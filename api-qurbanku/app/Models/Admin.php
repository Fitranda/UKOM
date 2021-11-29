<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Admin extends Model
{
    //
    protected $fillable = [
        'admin','telp', 'email','password','alamat','username','status'
    ];
}
