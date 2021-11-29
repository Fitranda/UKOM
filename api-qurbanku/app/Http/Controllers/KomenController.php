<?php

namespace App\Http\Controllers;

use App\Models\Komen;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class KomenController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $data = DB::table('komens')
        ->join('pelanggans','komens.idpelanggan','=','pelanggans.idpelanggan')
        ->join('penjuals','komens.idpenjual','=','penjuals.idpenjual')
        ->select('komens.*','pelanggans.pelanggan','pelanggans.gambar','penjuals.penjual','penjuals.ratings')
        ->orderBy('komens.tgl','desc')
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
            'idpelanggan'=>'required',
            'idpenjual'=>'required',
            'rating'=>'required',
            'komen'=>'required',
            'tgl'=>'required'
        ]);
        $kategori = Komen::create($request->all());
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
     * @param  \App\Models\Komen  $komen
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
        $data = DB::table('komens')
        ->join('pelanggans','komens.idpelanggan','=','pelanggans.idpelanggan')
        ->join('penjuals','komens.idpenjual','=','penjuals.idpenjual')
        ->select('komens.*','pelanggans.pelanggan','pelanggans.gambar','penjuals.penjual','penjuals.ratings')
        ->where('komens.idpenjual','=',$id)
        ->orderBy('komens.tgl','desc')
        ->get();

        return response()->json($data);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Komen  $komen
     * @return \Illuminate\Http\Response
     */
    public function edit(Komen $komen)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Komen  $komen
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Komen $komen)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Komen  $komen
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
        $menu = Komen::where('idkomen',$id)->delete();

        if ($menu) {
            return response()->json([
                'pesan' => 'data sudah dihapus'
            ]);
        }
    }
}
