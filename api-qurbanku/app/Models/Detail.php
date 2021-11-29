<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Detail extends Model
{
    //
    protected $fillable = [
        'notransaksi','idhewan', 'hargajual', 'flagada'
    ];
}
