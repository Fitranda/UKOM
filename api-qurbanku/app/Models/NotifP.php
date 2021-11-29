<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class NotifP extends Model
{
    //
    protected $fillable = [
        'idpelanggan', 'idpenjual','notif','tanggal','waktu'
      ];
}
