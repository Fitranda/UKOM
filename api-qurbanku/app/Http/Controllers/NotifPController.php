<?php

namespace App\Http\Controllers;

use App\Models\NotifP;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class NotifPController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index($id)
    {
        //
        $data = DB::table('notif_p_s')
        ->join('pelanggans','pelanggans.idpelanggan','=','notif_p_s.idpelanggan')
        ->join('penjuals','penjuals.idpenjual','=','notif_p_s.idpenjual')
        ->select('notif_p_s.*','pelanggans.pelanggan','penjuals.*')
        ->where('notif_p_s.idpelanggan','=',$id)
        ->orderBy('notif_p_s.tanggal','desc')
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
        $kategori = NotifP::create($request->all());
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
     * @param  \App\Models\NotifP  $notifP
     * @return \Illuminate\Http\Response
     */
    public function show(NotifP $notifP)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\NotifP  $notifP
     * @return \Illuminate\Http\Response
     */
    public function edit(NotifP $notifP)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\NotifP  $notifP
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, NotifP $notifP)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\NotifP  $notifP
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
        $menu = NotifP::where('idnotif',$id)->delete();

        if ($menu) {
            return response()->json([
                'pesan' => 'data sudah dihapus'
            ]);
        }
    }
}
