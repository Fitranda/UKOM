<?php

namespace App\Http\Controllers;

use App\Models\NotifT;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class NotifTController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index($id)
    {
        //
        $data = DB::table('notif_t_s')
        ->join('pelanggans','pelanggans.idpelanggan','=','notif_t_s.idpelanggan')
        ->join('penjuals','penjuals.idpenjual','=','notif_t_s.idpenjual')
        ->select('notif_t_s.*','pelanggans.*','penjuals.penjual')
        ->where('notif_t_s.idpenjual','=',$id)
        ->orderBy('notif_t_s.tanggal','desc')
        ->get();

        return response()->json($data);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create(Request $request)
    {
        //
        $this->validate($request,[
            'notif'=>'required',
            'idpenjual'=>'required',
            'idpelanggan'=>'required',
            'tanggal'=>'required',
            'waktu'=>'required'
        ]);
        $kategori = NotifT::create($request->all());
        if ($kategori) {
            return response()->json([
                'pesan' => 'Data Sudah Disimpan'
            ]);
        }
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\NotifT  $notifT
     * @return \Illuminate\Http\Response
     */
    public function show(NotifT $notifT)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\NotifT  $notifT
     * @return \Illuminate\Http\Response
     */
    public function edit(NotifT $notifT)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\NotifT  $notifT
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, NotifT $notifT)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\NotifT  $notifT
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
        $menu = NotifT::where('idnotif',$id)->delete();

        if ($menu) {
            return response()->json([
                'pesan' => 'data sudah dihapus'
            ]);
        }
    }
}
